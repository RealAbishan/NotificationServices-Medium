package com.eliteprogramming.noticationservice.service;

import com.eliteprogramming.noticationservice.dto.EmailDto;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    Object sendMail(EmailDto emailDto);

}
