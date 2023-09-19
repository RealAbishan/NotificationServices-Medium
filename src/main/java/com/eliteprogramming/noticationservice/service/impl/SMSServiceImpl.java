package com.eliteprogramming.noticationservice.service.impl;

import com.eliteprogramming.noticationservice.dto.Exception;
import com.eliteprogramming.noticationservice.enums.ErrorCodeEnum;
import com.eliteprogramming.noticationservice.service.SMSService;
import com.eliteprogramming.noticationservice.util.ENVConfig;
import com.eliteprogramming.noticationservice.util.TwilioConfiguration;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SMSServiceImpl implements SMSService {

    private final TwilioConfiguration twilioConfiguration;
    private final ENVConfig envConfig;


    @Override
    public Object sendSms(String phoneNumber, String message) {
        try {
            if (isPhoneNumberValid(phoneNumber)) {
                PhoneNumber to = new PhoneNumber(phoneNumber);
                PhoneNumber from = new PhoneNumber(envConfig.getTwilioSmsFromNo());
                return Message.creator(to,from,message).create();
            } else {
                throw new IllegalArgumentException(
                        "Phone number [" + phoneNumber + "] is not a valid number"
                );
            }
        } catch (ApiException exception) {
            throw new Exception(exception.getMessage(), HttpStatus.BAD_REQUEST, ErrorCodeEnum.OTP_SEND_ISSUE);
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }

}
