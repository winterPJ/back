package com.example.back.controller;

import com.example.back.dto.AuthCodeDTO;
import com.example.back.dto.EmailRequestDTO;
import com.example.back.service.EmailAuthService;
import com.example.back.utils.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.back.utils.Validation.isValidEmail;

@RestController
@RequestMapping("/user/auth")
public class EmailAuthController {

    private final EmailAuthService emailAuthService;

    @Autowired
    public EmailAuthController(EmailAuthService emailAuthService) {
        this.emailAuthService = emailAuthService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendEmailController(@RequestBody EmailRequestDTO emailRequestDTO) throws IOException {
        ApiResponse response = new ApiResponse();
        try {
            if (emailRequestDTO.getEmail() == null || emailRequestDTO.getEmail().isEmpty()) {
                throw new IllegalArgumentException("이메일을 입력해주세요.");
            } else if (!isValidEmail(emailRequestDTO.getEmail())) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
            }

            emailAuthService.sendAuthEmail(emailRequestDTO);
            response.setSuccess(true);
            response.setData("인증번호가 발송되었습니다.");
        } catch (IOException e) {
            response.setSuccess(false);
            response.setData(e.getMessage());
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setData(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyCode(@RequestBody AuthCodeDTO authCodeDTO) {
        ApiResponse response = new ApiResponse();
        if(authCodeDTO.getEmail() == null || authCodeDTO.getEmail().isEmpty()) {
            response.setSuccess(false);
            response.setData("이메일을 입력해주세요.");
            return ResponseEntity.ok(response);
        }
        if(authCodeDTO.getAuth_code() == null || authCodeDTO.getAuth_code().isEmpty()) {
            response.setSuccess(false);
            response.setData("인증번호를 입력해주세요.");
            return ResponseEntity.ok(response);
        }
        boolean isVerified = emailAuthService.verifyAuthCode(authCodeDTO);

        if (!isVerified) {
            response.setSuccess(false);
            response.setData("일치하지 않거나 유효하지 않은 코드입니다.");
            return ResponseEntity.ok(response);
        }
        response.setSuccess(true);
        response.setData("인증되었습니다.");

        return ResponseEntity.ok(response);
    }

}
