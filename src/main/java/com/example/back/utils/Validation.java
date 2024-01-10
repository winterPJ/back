package com.example.back.utils;

public class Validation {
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
    public static boolean isValidPassword(String password) { 
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"; 
        return password.matches(passwordRegex); 
    } 
    public static boolean isValidNickname(String nickname) { 
        String nicknameRegex = "^[가-힣a-zA-Z0-9]{3,8}$"; 
        return nickname.matches(nicknameRegex); 
    }
    public static boolean PasswordCheck(String password, String passwordConfirm) {
        return password.matches(passwordConfirm);
  }
}
