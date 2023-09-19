package com.eliteprogramming.noticationservice.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class OtpGenerate {
    private static final int OTP_LENGTH = 6;
    private static final String OTP_NUMBERS_NON_ZERO = "123456789";

    private static final String OTP_NUMBERS = "0".concat(OTP_NUMBERS_NON_ZERO);
    private static final int ONE = 1;

    public int getOtpCode() {
        Random random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++)
            builder.append(OTP_NUMBERS.charAt(random.nextInt(OTP_NUMBERS.length())));
        String otp = builder.toString();
        if (otp.startsWith("0")) {
            otp = otp.substring(ONE);
            otp = String.valueOf(OTP_NUMBERS_NON_ZERO.charAt(random.nextInt(OTP_NUMBERS_NON_ZERO.length()))).concat(otp);
        }
        return Integer.parseInt(otp);
    }
}
