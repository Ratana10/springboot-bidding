package com.assignment.bidding.controller;

import com.assignment.bidding.dto.UserDto;
import com.assignment.bidding.mapper.BidMapper;
import com.assignment.bidding.mapper.ItemMapper;
import com.assignment.bidding.mapper.UserMapper;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.User;
import com.assignment.bidding.service.BidService;
import com.assignment.bidding.service.ItemService;
import com.assignment.bidding.service.UserService;
import com.assignment.bidding.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final BidService bidService;
    private final BidMapper bidMapper;
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping("/bid-history")
    public ResponseEntity<?> viewBidHistory() {
        Long bidderId = Utils.getUserIdFromContext();
        List<Bid> bids = bidService.viewBidHistory(bidderId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

    @GetMapping("/win-history")
    public ResponseEntity<?> viewWinHistory() {
        Long bidderId = Utils.getUserIdFromContext();
        List<Bid> bids = bidService.viewWinBidHistory(bidderId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

    @GetMapping("/lost-history")
    public ResponseEntity<?> viewLostHistory() {
        Long bidderId = Utils.getUserIdFromContext();

        List<Bid> bids = bidService.viewLostBidHistory(bidderId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

    @GetMapping("/items")
    public ResponseEntity<?> viewAllItemByOwner() {
        Long clientId = Utils.getUserIdFromContext();
        List<Item> items = itemService.getItemsByOwner(clientId);
        return ResponseEntity.ok(items.stream().map(itemMapper::toItemDto));
    }


}
