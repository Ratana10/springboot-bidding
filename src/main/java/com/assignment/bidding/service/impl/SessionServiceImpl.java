package com.assignment.bidding.service.impl;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.repository.SessionRepository;
import com.assignment.bidding.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session startNewSession(Item item, Bid bid) {
        Session session = Session.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusMinutes(5))
                .highestAmount(bid.getAmount())
                .item(item)
                .status(Status.ACTIVE)
                .build();
        return sessionRepository.save(session);
    }

    @Override
    public Session updateHigherBid(Session session, Bid bid) {
        session.setWinningBid(bid);
        session.setHighestAmount(bid.getAmount());
        return sessionRepository.save(session);
    }
}
