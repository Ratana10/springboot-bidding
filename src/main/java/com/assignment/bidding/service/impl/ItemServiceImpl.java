package com.assignment.bidding.service.impl;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.repository.ItemRepository;
import com.assignment.bidding.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    @Override
    public Item postItem(Item item) {
        item.setStatus(Status.ACTIVE);
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(()-> new RuntimeException("not found"));
    }

    @Override
    public List<Item> getActiveItems() {
        return itemRepository.findByStatus(Status.ACTIVE);
    }

    @Override
    public void updateSession(Long itemId, Session session) {
        Item item = findById(itemId);
        item.setSession(session);
        itemRepository.save(item);
    }
}
