package com.example.jl_entities.controller;

import lombok.Data;

@Data
public class SMSSendRequest {

    private Long clientId;
    private String destinationSMSNumber;
    private String smsMessage;

}
