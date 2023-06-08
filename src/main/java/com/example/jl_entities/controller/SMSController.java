package com.example.jl_entities.controller;

import com.example.jl_entities.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
@Slf4j
public class SMSController {
    private final SMSService smsService;

    @Autowired
    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/sendsms")
    public String sendSMS(@RequestBody SMSSendRequest sendRequest){

        String smsNumber = sendRequest.getDestinationSMSNumber();
        String generatedCode = generateCode();

        String smsMessage = "Your verification code is: " + generatedCode;

        String status = smsService.sendSMS(smsNumber, smsMessage);

        if (status.equals("queued") || status.equals("sent")) {
            return "SMS sent successfully";
        } else {
            return "Failed to send SMS";
        }
    }

    private String generateCode() {
        int min = 100000;
        int max = 999999;
        return String.valueOf((int) (Math.random() * (max - min + 1) + min));
    }
}
