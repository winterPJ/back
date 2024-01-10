package com.example.back.dto;

import com.example.back.utils.Datetime;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthCodeDTO {
    private String email;
    private String auth_code;
    private LocalDateTime created_at;

    public AuthCodeDTO(String email, String authCode) {
        this.email = email;
        this.auth_code = authCode;
        this.created_at = Datetime.nowInKst();
    }
}

