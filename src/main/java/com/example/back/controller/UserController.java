package com.example.back.controller;

import com.example.back.dto.LoginDTO;
import com.example.back.dto.UserDTO;
import com.example.back.service.UserService;
import com.example.back.utils.Validation;
import com.example.back.utils.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/validation/email")
    public ResponseEntity<ApiResponse> validateEmail(@RequestBody UserDTO user) {
        ApiResponse response = new ApiResponse();
        if (!Validation.isValidEmail(user.getEmail())) {
            response.setSuccess(false);
            response.setData("이메일 형식을 확인해주세요.");
            System.out.println(user.getEmail());
        } else if (userService.isEmailDuplicated(user.getEmail())) {
            response.setSuccess(false);
            response.setData("이미 등록된 이메일입니다.");
        } else {
            response.setSuccess(true);
            System.out.println(user.getEmail());
            response.setData("사용 가능한 이메일입니다.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validation/password")
    public ResponseEntity<ApiResponse> validatePassword(@RequestBody UserDTO user) {
        ApiResponse response = new ApiResponse();
        if (!Validation.isValidPassword(user.getPassword())) {
            response.setSuccess(false);
            response.setData("비밀번호는 영문, 숫자, 특수문자의 조합으로 8자 이상이어야 합니다.");
        } else {
            response.setSuccess(true);
            response.setData("유효한 비밀번호입니다.");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validation/passwordCheck")
    public ResponseEntity<ApiResponse> checkPassword(@RequestBody UserDTO user) {
        ApiResponse response = new ApiResponse();
        if (!Validation.PasswordCheck(user.getPassword(), user.getPasswordConfirm())) {
            response.setSuccess(false);
            response.setData("비밀번호가 일치하지 않습니다.");
        } else {
            response.setSuccess(true);
            response.setData("비밀번호 일치");
        }
        return ResponseEntity.ok(response);


    }

    @PostMapping("/validation/nickname")
    public ResponseEntity<ApiResponse> validateNickname(@RequestBody UserDTO user) {
        ApiResponse response = new ApiResponse();
        if (!Validation.isValidNickname(user.getNickname())) {
            response.setSuccess(false);
            response.setData("닉네임은 한글이나 영문 또는 숫자 조합으로 3~8자 이어야 합니다.");
        } else if (userService.isNicknameDuplicated(user.getNickname())) {
            response.setSuccess(false);
            response.setData("이미 등록된 닉네임입니다.");
        } else {
            response.setSuccess(true);
            response.setData("사용 가능한 닉네임입니다.");
        }
        return ResponseEntity.ok(response);

    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody UserDTO userDTO) {
        ApiResponse response = new ApiResponse();
        if (!Validation.isValidEmail(userDTO.getEmail())) {
            response.setSuccess(false);
            response.setData("유효하지 않은 이메일 형식입니다.");

        } else if (!Validation.isValidPassword(userDTO.getPassword())) {
            response.setSuccess(false);
            response.setData("비밀번호는 영문, 숫자, 특수문자의 조합으로 8자 이상이어야 합니다.");
        } else if (!Validation.isValidNickname(userDTO.getNickname())) {
            response.setSuccess(false);
            response.setData("닉네임은 한글이나 영문 또는 숫자 조합으로 3~8자 이어야 합니다.");
        } else if (userService.isEmailDuplicated(userDTO.getEmail())) {
            response.setSuccess(false);
            response.setData("이미 등록된 이메일입니다.");
        } else if (userService.isNicknameDuplicated(userDTO.getNickname())) {
            response.setSuccess(false);
            response.setData("이미 사용중인 닉네임입니다.");
        } else if (!Validation.PasswordCheck(userDTO.getPassword(), userDTO.getPasswordConfirm())) {
            response.setSuccess(false);
            response.setData("비밀번호가 일치하지 않습니다.");
        } else {
            response.setSuccess(true);
            response.setData("회원가입이 완료되었습니다.");
            userService.insertUserData(userDTO);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find/id")
    public ResponseEntity<ApiResponse> findIdByEmail(@RequestBody UserDTO userDTO) {
        ApiResponse response = new ApiResponse();
        if (!Validation.isValidEmail(userDTO.getEmail())) {
            response.setSuccess(false);
            response.setData("유효하지 않은 이메일 형식입니다.");
        } else if (!userService.isEmailDuplicated(userDTO.getEmail())) {
            response.setSuccess(false);
            response.setData("등록되지 않은 이메일입니다.");
        } else {
            response.setSuccess(true);
            response.setData(userService.findIdByEmail(userDTO.getEmail()));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDto, HttpSession session) {
        LoginDTO user = userService.login(loginDto.getEmail(), loginDto.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return "Login successful";
        }
        return "Login failed";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }


}

