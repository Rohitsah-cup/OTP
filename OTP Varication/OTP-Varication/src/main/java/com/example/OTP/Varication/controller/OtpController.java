package com.example.OTP.Varication.controller;
import com.example.OTP.Varication.payload.PhoneRequest;
import com.example.OTP.Varication.payload.VerifyRequest;
import com.example.OTP.Varication.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/generate")
    public String generateOtp(@RequestBody PhoneRequest phoneRequest) {
        String phoneNumber = phoneRequest.getPhoneNumber();
        String otp = otpService.generateOtp();
        otpService.sendOtp(phoneNumber, otp);
        return "OTP generated and sent successfully";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestBody VerifyRequest verifyRequest) {
        String phoneNumber = verifyRequest.getPhoneNumber();
        String otp = verifyRequest.getOtp();

        if (otpService.verifyOtp(phoneNumber, otp)) {
            return "verify ";
        } else {
            return "Invalid OTP";
        }
    }
}

