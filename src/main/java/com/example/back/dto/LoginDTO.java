package com.example.back.dto;

import com.example.back.utils.Datetime;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LoginDTO {
    private String email;
    private String password;

}
