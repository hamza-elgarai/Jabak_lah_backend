package com.example.jl_entities.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SMSService {

    @Value("${twilio.account-sid}")
    String account_sid;

    @Value("${twilio.auth-token}")
    String auth_token;

    @Value("${twilio.trial-phone-number}")
    String trial_phone_number;


    @PostConstruct
    private void setup(){
        Twilio.init(account_sid, auth_token);
    }

    public String sendSMS(String smsNumber, String smsMessage){
        Twilio.init(account_sid, auth_token);

        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(trial_phone_number),
                smsMessage).create();

        return message.getStatus().toString();
    }

}
