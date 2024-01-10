package com.example.back.configuration;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordEncryption {
    public static String hashPassword(String email, String password){

        String toReturn = null;
        String input = email + password;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

}
