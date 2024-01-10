package com.example.back.configuration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
    public static String hashPassword(String email, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            String plainText = email + password;
            byte[] digest = md.digest(plainText.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed hashing password", e);
        }
    }
}
