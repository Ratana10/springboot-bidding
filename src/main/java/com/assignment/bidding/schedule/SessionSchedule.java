package com.assignment.bidding.schedule;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Session;
import com.assignment.bidding.repository.SessionRepository;
import com.assignment.bidding.service.SessionService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionSchedule {
    private final SessionService sessionService;

    @Scheduled(fixedRate = 60000)  //60000=1min
    private void EndSession() throws TemplateException, MessagingException, IOException {
        List<Session> sessions = sessionService.findByStatus(Status.ACTIVE);

        if (sessions.isEmpty()) {
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        for (Session session : sessions) {
            if (now.isAfter(session.getEndTime())) {
                sessionService.endSession(session);
            }
        }
    }


}
