package com.eliteprogramming.noticationservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ENVConfig {


    @Value("${env.twilio.sms.authToken}")
    private String authToken;
    @Value("${env.twilio.sms.accountSid}")
    private String accountSid;
    @Value("${env.twilio.sms.fromNo}")
    private String twilioSmsFromNo;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getAuthToken() {
        return authToken;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getTwilioSmsFromNo() {
        return twilioSmsFromNo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }
}
