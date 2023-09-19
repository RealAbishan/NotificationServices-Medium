## _Implementing Twilio SMS API with a Spring Boot Application_

## Introduction

In today's fast-paced digital world, communication is key, and Short Message Service (SMS) remains a reliable and effective means of reaching users. Twilio, a cloud communications platform, offers a robust SMS API that allows developers to integrate SMS capabilities into their applications seamlessly. This article will guide you through the process of implementing the Twilio SMS API with a Spring Boot application, enabling you to send SMS messages programmatically.

- Type some Markdown on the left
- See HTML in the right
- ✨Magic ✨

## Prerequisites
Before we dive into the implementation, ensure you have the following prerequisites in place:
1. **Java and Spring Boot:** A basic understanding of Java and Spring Boot is essential for this tutorial.
2. **Twilio Account:** Sign up for a Twilio account if you haven't already. You'll need your Twilio Account SID and Auth Token, which can be found in your Twilio Console.
   Once you sign up or log in to the Twilio account you can get the Account SID and Auth Token from Twilio Console.
3. **Twilio Phone Number:** Obtain a Twilio phone number that you can use to send SMS messages.
4. **Maven or Gradle:** Choose either Maven or Gradle as your build tool for managing project dependencies.

- Import a HTML file and watch it magically convert to Markdown
- Drag and drop images (requires your Dropbox account be linked)
- Import and save files from GitHub, Dropbox, Google Drive and One Drive
- Drag and drop markdown and HTML files into Dillinger
- Export documents as Markdown, HTML and PDF

## Getting Started
1. Create a Spring Boot Project
   Start by creating a new Spring Boot project using your preferred IDE or Spring Initializer (https://start.spring.io/). Make sure to include the required dependencies: Spring Web, Thymeleaf (optional), and Spring Boot DevTools.
2. Add Twilio SDK Dependency
   In your project's `pom.xml` (for Maven) or `build.gradle` (for Gradle), add the Twilio SDK dependency:
```<! - Maven →
 <dependency>
 <groupId>com.twilio.sdk</groupId>
 <artifactId>twilio</artifactId>
 <version>8.24.0</version> <! - Check for the latest version →
 </dependency>
```
3. Configuration
   Create a configuration file (`application.properties` or `application.yml`) in your Spring Boot project to store your Twilio Account SID, Auth Token, and Twilio phone number:
```properties
 # Twilio Configuration
 twilio.accountSid=your_account_sid
 twilio.authToken=your_auth_token
 twilio.phoneNumber=your_twilio_phone_number
```
Replace `your_account_sid`, `your_auth_token`, and `your_twilio_phone_number` with your Twilio credentials.

## Implementing Twilio SMS API
Now, let's implement the code to send SMS messages using the Twilio SMS API:

#### Java　
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

This service class initializes Twilio using your credentials and provides a method `sendSms` to send SMS messages. You can inject this service into your controllers or services to send SMS messages from various parts of your application.
## Creating an SMS Controller
To test the Twilio SMS API integration, create an SMS controller that exposes an endpoint for sending SMS messages:

#### Java
package com.eliteprogramming.noticationservice.controller;

import com.eliteprogramming.noticationservice.dto.Const;
import com.eliteprogramming.noticationservice.dto.OtpDto;
import com.eliteprogramming.noticationservice.dto.Response;
import com.eliteprogramming.noticationservice.dto.ResponseController;
import com.eliteprogramming.noticationservice.service.SMSService;
import com.eliteprogramming.noticationservice.util.OtpGenerate;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
@RestController
@RequestMapping("/messageService")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class SMSController extends ResponseController {

    private final SMSService smsService;
    private final OtpGenerate otpGenerate;

    @ApiIgnore
    @PostMapping("/sms/sendOtp")
    @ApiOperation(value = "Send OTP", notes = "Send OTP message to the given Phone Number")
    @ApiResponse(code = 200, message = "Success", response = Response.class)
    public ResponseEntity<Object> sendOtp(@Valid @RequestBody OtpDto dto) {
        int otp = otpGenerate.getOtpCode();
        Message twilioMessage = (Message) smsService.sendSms(dto.getPhoneNumber(),Const.OTP_MESSAGE.replace("<otp>",Integer.toString(otp)) );
        log.info("Twilio Response : {}", twilioMessage.getStatus());

        if (twilioMessage.getStatus().toString().equals("queued")) {
            return new ResponseEntity<Object>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}

In this controller, we inject the `SMSService` and create a POST endpoint at `/messageService/sms/otp` that accepts `to` (recipient phone number) and `messageBody` as request parameters.
## Testing the SMS Endpoint
You can now test the SMS sending functionality by making a POST request to `/messageService/sms/otp` with the recipient's phone number and the message body.

## Conclusion
Integrating the Twilio SMS API with a Spring Boot application allows you to add SMS communication capabilities to your software effortlessly. In this tutorial, we covered the essential steps, including setting up a Spring Boot project, configuring Twilio credentials, and implementing the Twilio SMS service. With this integration in place, you can build applications that leverage SMS for notifications, verifications, and more, enhancing user engagement and communication.
You can refer the following Github repository for further Assiatance: