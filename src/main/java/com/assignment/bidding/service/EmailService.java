package com.assignment.bidding.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Map;

public interface EmailService {
    void sendEmail(String to, String subject, Map<String, Object> model) throws MessagingException, IOException, TemplateException;
}
