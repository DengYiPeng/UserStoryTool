package edu.nju.stories.service;

public interface MailService {

    void sendEmail(String email, String subject, String htmlBody);
}
