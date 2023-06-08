package com.example.jl_entities.controller;

import lombok.Data;

@Data
public class SMSSendRequest {

    private String destinationSMSNumber;
    private String smsMessage;

}
