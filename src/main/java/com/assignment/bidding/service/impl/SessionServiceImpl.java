package com.assignment.bidding.service.impl;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.model.User;
import com.assignment.bidding.repository.ItemRepository;
import com.assignment.bidding.repository.SessionRepository;
import com.assignment.bidding.service.EmailService;
import com.assignment.bidding.service.SessionService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final EmailService emailService;
    private final ItemRepository itemRepository;

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
    public void endSession(Session session) throws TemplateException, MessagingException, IOException {
        Bid winningBid = session.getWinningBid();
        Item item = session.getItem();
        User bidder = winningBid.getBidder();

        // Update session status to COMPLETE
        session.setStatus(Status.COMPLETED);
        sessionRepository.save(session);

        // Update Item status to COMPLETE
        item.setStatus(Status.COMPLETED);
        itemRepository.save(item);

        String email = bidder.getEmail();
        String subject = "Congratulations! You won the bid";

        HashMap<String, Object> model = new HashMap<>();
        model.put("username", bidder.getUsername());
        model.put("item", item.getName());
        model.put("amount", winningBid.getAmount());

        emailService.sendEmail(email, subject, model);
    }

    @Override
    public void updateHigherBid(Session session, Bid bid) {
        session.setWinningBid(bid);
        session.setHighestAmount(bid.getAmount());
        sessionRepository.save(session);
    }

    @Override
    public List<Session> findByStatus(Status status) {
        return sessionRepository.findByStatus(status);
    }

}
