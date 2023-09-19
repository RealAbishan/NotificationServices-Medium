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
