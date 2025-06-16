package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail[], String subject, String body) {

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setText(body);
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);

            javaMailSender.send(simpleMailMessage);
            log.info("Mail sent successfully to all drivers for the ride");
        }
        catch (Exception e){
            log.info("Mail send failed "+e.getMessage());

        }

    }
}
