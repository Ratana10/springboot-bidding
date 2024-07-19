package com.assignment.bidding.service;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface SessionService {
    Session startNewSession(Item item, Bid bid);
    void endSession(Session session) throws TemplateException, MessagingException, IOException;

    void updateHigherBid(Session session, Bid bid);
    List<Session> findByStatus(Status status);
}
