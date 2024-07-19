package com.assignment.bidding.service;

import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;

public interface SessionService {
    Session save(Session session);
    Session startNewSession(Item item, Bid bid);

    Session updateHigherBid(Session session, Bid bid);
}
