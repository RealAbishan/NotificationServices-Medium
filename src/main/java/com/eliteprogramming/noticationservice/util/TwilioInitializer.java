package com.eliteprogramming.noticationservice.util;

import com.twilio.Twilio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {


    private final TwilioConfiguration twilioConfiguration;
    private final ENVConfig envConfig;

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration, ENVConfig envConfig) {
        this.twilioConfiguration = twilioConfiguration;
        this.envConfig = envConfig;
        Twilio.init(
                envConfig.getAccountSid(),
                envConfig.getAuthToken()
        );
    }
}