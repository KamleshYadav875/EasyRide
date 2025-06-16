package com.easyride.easyRideApp.services;

public interface EmailSenderService {

    void sendEmail(String toEmail, String subject,String body );

}
