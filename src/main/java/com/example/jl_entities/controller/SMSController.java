package com.example.jl_entities.controller;

import com.example.jl_entities.entity.VerificationCode;
import com.example.jl_entities.repository.ClientRepository;
import com.example.jl_entities.repository.VerificationCodeRepository;
import com.example.jl_entities.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api")
@Slf4j
public class SMSController {
    private final SMSService smsService;
    private final ClientRepository clientRepository;
    private final VerificationCodeRepository verificationCodeRepository;

    @Autowired
    public SMSController(SMSService smsService,ClientRepository clientRepository,VerificationCodeRepository verificationCodeRepository) {
        this.smsService = smsService;
        this.verificationCodeRepository = verificationCodeRepository;
        this.clientRepository = clientRepository;
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
    @PostMapping("/sendfakesms")
    public Map<String,String> sendFakeSMS(@RequestBody SMSSendRequest sendRequest){

        String smsNumber = sendRequest.getDestinationSMSNumber();
        String generatedCode = generateCode();
        List<VerificationCode> verificationCode = verificationCodeRepository.findByClientId(sendRequest.getClientId());
        if(verificationCode.size()==0){
            VerificationCode v = new VerificationCode(null,generatedCode,clientRepository.findById(sendRequest.getClientId()).orElse(null));
            verificationCodeRepository.saveAndFlush(v);
        }
        else{
            verificationCode.get(0).setCode(generatedCode);
            verificationCodeRepository.saveAndFlush(verificationCode.get(0));
        }
        String smsMessage = "Your verification code is: " + generatedCode;
        System.out.println(smsMessage);
        String status = smsService.sendVonageSms(smsNumber, smsMessage);
        if (status.equals("success")) {
            return Map.of("message","L'SMS est envoyé");
        } else {
            return Map.of("messsage","Echec de l'envoi de l'SMS");
        }

    }

    private String generateCode() {
        int min = 100000;
        int max = 999999;
        return String.valueOf((int) (Math.random() * (max - min + 1) + min));
    }
}
