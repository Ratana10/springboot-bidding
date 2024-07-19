package com.assignment.bidding.schedule;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Bid;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionSchedule {
    private final SessionRepository sessionRepository;

    @Scheduled(fixedRate = 60000)  //60000=1min
    private void EndSession() {
        List<Session> sessions = sessionRepository.findByStatus(Status.ACTIVE);
        if (sessions.isEmpty()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Session session : sessions) {
            if (now.isAfter(session.getEndTime())) {
                endBidding(session);
            }
        }

        System.out.println("sent");

    }

    private void endBidding(Session session) {
        Bid winningBid = session.getWinningBid();

        // Update session status to INACTIVE
        session.setStatus(Status.INACTIVE);
        sessionRepository.save(session);
    }
}
