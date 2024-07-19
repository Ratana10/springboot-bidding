package com.assignment.bidding.service.impl;

import com.assignment.bidding.enums.BidStatus;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.repository.BidRepository;
import com.assignment.bidding.service.BidService;
import com.assignment.bidding.service.ItemService;
import com.assignment.bidding.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final ItemService itemService;
    private final SessionService sessionService;

    @Override
    public List<Bid> viewItemBidHistory(Long itemId) {
        return bidRepository.findByItemId(itemId);
    }

    @Override
    public Bid postBid(Bid bid) {
        // start session if it is the first bid
        Item item = bid.getItem();
        Session session = item.getSession();
        LocalDateTime now = LocalDateTime.now();


        // create first session for the item
        if (session == null) {
            session = sessionService.startNewSession(item, bid);
            itemService.updateSession(item.getId(), session);
        } else {
            // check if session has end
            if (now.isAfter(session.getEndTime())) {
                throw new RuntimeException("bidding session has ended");
            }
            // check bid amount compare to the previous amount
            if (bid.getAmount().compareTo(session.getHighestAmount()) <= 0) {
                throw new RuntimeException("bid amount must be higher than current bid");
            }

            // update the previous bid set to lost
            Bid previousBid = session.getWinningBid();
            previousBid.setStatus(BidStatus.LOST);
            bidRepository.save(previousBid);
        }


        // save the bid
        bid.setDate(now);
        bid.setStatus(BidStatus.WIN);
        bidRepository.save(bid);

        // update session with higher bid
        sessionService.updateHigherBid(session, bid);
        return bid;
    }

    @Override
    public List<Bid> viewBidHistory(Long bidderId) {
        return bidRepository.findByBidderId(bidderId);
    }

    @Override
    public List<Bid> viewWinBidHistory(Long bidderId) {
        return bidRepository.findByBidderIdAndStatus(bidderId, BidStatus.WIN);
    }

    @Override
    public List<Bid> viewLostBidHistory(Long bidderId) {
        return bidRepository.findByBidderIdAndStatus(bidderId, BidStatus.LOST);
    }

    @Override
    public Bid findById(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
