package com.eliteprogramming.noticationservice.service.impl;

import com.eliteprogramming.noticationservice.dto.EmailDto;
import com.eliteprogramming.noticationservice.dto.ErrorMessage;
import com.eliteprogramming.noticationservice.dto.Response;
import com.eliteprogramming.noticationservice.enums.ErrorCodeEnum;
import com.eliteprogramming.noticationservice.enums.ResponseMessage;
import com.eliteprogramming.noticationservice.service.EmailService;
import com.eliteprogramming.noticationservice.util.ENVConfig;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public Object sendMail(EmailDto emailDto) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(emailDto.getToList());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessageHelper.setText(emailDto.getBody());

            javaMailSender.send(mimeMessage);

            log.info("Message Sent Successfully to: {}", emailDto.getToList());
            return new Response("Email Sent Successfully",emailDto.getToList(),ResponseMessage.SUCCESS);
        }
          catch (Exception e) {
            log.error("sendEmail() | Error : {}", e.getMessage());
            throw new RuntimeException(e);
        }


    }


}
