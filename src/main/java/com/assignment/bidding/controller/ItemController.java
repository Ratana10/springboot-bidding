package com.assignment.bidding.controller;

import com.assignment.bidding.dto.ItemDto;
import com.assignment.bidding.mapper.BidMapper;
import com.assignment.bidding.mapper.ItemMapper;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.service.BidService;
import com.assignment.bidding.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CLIENT')")
public class ItemController {
    private final ItemService itemService;
    private final BidService bidService;
    private final ItemMapper itemMapper;
    private final BidMapper bidMapper;


    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDto dto) {
        Item item = itemMapper.toItem(dto);
        item = itemService.postItem(item);
        return ResponseEntity.ok(itemMapper.toItemDto(item));
    }

    @GetMapping("{itemId}/bid-history")
    public ResponseEntity<?> viewItemBidHistory(@PathVariable Long itemId) {
        List<Bid> bids = bidService.viewItemBidHistory(itemId);
        return ResponseEntity.ok(bids.stream().map(bidMapper::toBidDto));
    }

}
