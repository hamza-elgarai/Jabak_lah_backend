package com.example.jl_entities.service;

public interface EmailService {
    String sendMail(String to, String subject, String body);
}
