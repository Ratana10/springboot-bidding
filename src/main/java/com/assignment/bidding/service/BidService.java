package com.assignment.bidding.service;

import com.assignment.bidding.model.Bid;

import java.util.List;

public interface BidService {
    Bid postBid(Bid bid);
    List<Bid> viewItemBidHistory(Long itemId);
    List<Bid> viewBidHistory(Long bidderId);
    List<Bid> viewWinBidHistory(Long bidderId);
    List<Bid> viewLostBidHistory(Long bidderId);
    Bid findById(Long bidId);


}
