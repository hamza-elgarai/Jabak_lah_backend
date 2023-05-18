package com.example.jl_entities.randomizer;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Randomizer {
    private Random random;
    private static final String alphabetUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String alphabetLowercase = "abcdefghijklmnopqrstuvwxyz";
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
    private static final String numbers = "0123456789";

    public static String generateAgentUser(){
        Random random = new Random();
        String username = RandomStringUtils.random( 5, alphabetUppercase )+RandomStringUtils.random(4,numbers);
        return username;
    }

}
