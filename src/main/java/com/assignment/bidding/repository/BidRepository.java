package com.assignment.bidding.repository;

import com.assignment.bidding.enums.BidStatus;
import com.assignment.bidding.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByBidderId(Long bidderId);
    List<Bid> findByItemId(Long itemId);
    List<Bid> findByBidderIdAndStatus(Long itemId, BidStatus status);

}
