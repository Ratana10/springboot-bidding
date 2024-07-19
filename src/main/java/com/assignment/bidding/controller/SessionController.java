package com.assignment.bidding.controller;

import com.assignment.bidding.mapper.ItemMapper;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        List<Item> items = itemService.getActiveItems();
        return ResponseEntity.ok(items.stream().map(itemMapper::toItemDto).toList());
    }


}
