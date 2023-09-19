package com.eliteprogramming.noticationservice.service;

import com.eliteprogramming.noticationservice.dto.OtpDto;
import com.twilio.rest.api.v2010.account.Message;

public interface SMSService {

    Object sendSms(String phoneNumber, String message);
}
