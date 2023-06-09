package com.example.jl_entities.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
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
    public String sendVonageSms(String number,String smsMessage) {
        System.out.println(number);
        //old key
//        VonageClient client = VonageClient.builder().apiKey("30ac1021").apiSecret("8fMBYUpb90TTMs0Q").build();
        //new key
        VonageClient client = VonageClient.builder().apiKey("1d32701f").apiSecret("N8pYofBmITPYVGXk").build();
        TextMessage message = new TextMessage("Vonage APIs",
                number,
                smsMessage
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            return "success";
        } else {
            System.out.println(response.getMessages().get(0).getErrorText());
            return "error";
        }
    }

}
