package com.example.OTP.Varication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private final SmsService smsService;
    private final Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    public OtpService(SmsService smsService) {
        this.smsService = smsService;
    }

    public String generateOtp() {
        Random random = new Random();
        int otp = 1000 + random.nextInt(9000); // Generate a random 4-digit OTP
        return Integer.toString(otp);
    }

    public void sendOtp(String phoneNumber, String otp) {
        String message = "Your OTP is: " + otp;
        smsService.sendSms(phoneNumber, message);
        otpStorage.put(phoneNumber, otp);
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        String storedOtp = otpStorage.get(phoneNumber);
        return storedOtp != null && storedOtp.equals(otp);
    }
}


