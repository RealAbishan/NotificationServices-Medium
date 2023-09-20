package com.eliteprogramming.noticationservice.controller;

import com.eliteprogramming.noticationservice.dto.EmailDto;
import com.eliteprogramming.noticationservice.dto.Response;
import com.eliteprogramming.noticationservice.dto.ResponseController;
import com.eliteprogramming.noticationservice.service.EmailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/emailService")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmailController {
    private final EmailService emailService;

    @ApiIgnore
    @PostMapping("/send")
    @ApiOperation(value = "Send EMail", notes = "Send Email to the given Email Address")
    @ApiResponse(code = 200, message = "Success", response = Response.class)
    public ResponseEntity<Object> sendMail(@Valid @RequestBody EmailDto emailDto) {
        log.info("HIT /send POST | Dto : {}", emailDto);
        return ResponseEntity.ok(emailService.sendMail(emailDto));
    }

}
