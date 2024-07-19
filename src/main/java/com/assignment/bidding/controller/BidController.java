package com.assignment.bidding.controller;

import com.assignment.bidding.dto.BidDto;
import com.assignment.bidding.mapper.BidMapper;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bids")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;
    private final BidMapper bidMapper;

    @PostMapping
    public ResponseEntity<?> createBid(@RequestBody BidDto dto) {
        Bid bid = bidMapper.toBid(dto);
        bid = bidService.postBid(bid);
        return ResponseEntity.ok(bidMapper.toBidDto(bid));
    }


}
