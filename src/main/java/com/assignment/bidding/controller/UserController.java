package com.assignment.bidding.controller;

import com.assignment.bidding.dto.UserDto;
import com.assignment.bidding.mapper.BidMapper;
import com.assignment.bidding.mapper.UserMapper;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.User;
import com.assignment.bidding.service.BidService;
import com.assignment.bidding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('BIDDER')")
public class UserController {
    private final BidService bidService;
    private final BidMapper bidMapper;

    @GetMapping("{userId}/bid-history")
    public ResponseEntity<?> viewBidHistory(@PathVariable Long userId) {
        List<Bid> bids = bidService.viewBidHistory(userId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

    @GetMapping("{userId}/win-history")
    public ResponseEntity<?> viewWinHistory(@PathVariable Long userId) {
        List<Bid> bids = bidService.viewWinBidHistory(userId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

    @GetMapping("{userId}/lost-history")
    public ResponseEntity<?> viewLostHistory(@PathVariable Long userId) {
        List<Bid> bids = bidService.viewLostBidHistory(userId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }


}
