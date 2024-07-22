package com.assignment.bidding.service;

import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;

import java.util.List;

public interface ItemService {
    Item postItem(Item item);
    Item findById(Long itemId);
    List<Item> getActiveItems();
    void updateSession(Long itemId, Session session);
    List<Item> getItemsByOwner(Long clientId);
}
