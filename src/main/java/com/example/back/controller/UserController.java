package com.example.back.controller;

//import com.example.back.dto.HotUserDTO;
import com.example.back.dto.UserDTO;
import com.example.back.service.UserService;
import com.example.back.utils.Validation;
import com.example.back.utils.response.ApiResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.back.configuration.PasswordEncryption;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    //private final HttpSession httpSession;


    @Autowired
    public UserController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        //this.httpSession = httpSession;
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
    public ResponseEntity<ApiResponse> login(@RequestBody UserDTO userDTO, HttpSession session) {
        ApiResponse response = new ApiResponse();

        String hassedPassword =  PasswordEncryption.hashPassword(userDTO.getEmail(), userDTO.getPassword());

        UserDTO user = userService.login(userDTO.getEmail(), hassedPassword);
        if (user == null) {
            response.setSuccess(false);
            response.setData("이메일 또는 비밀번호가 잘못되었습니다.");
            return ResponseEntity.ok(response);
        }

        session.setAttribute("user", user);
        response.setSuccess(true);
        response.setData("로그인 성공");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        session.removeAttribute("user");
        ApiResponse response = new ApiResponse(true, "로그아웃 성공");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check")
    public ApiResponse checkSession(HttpSession session) {
        ApiResponse response = new ApiResponse();

        if (session.getAttribute("user") != null) {
            response.setSuccess(true);
            response.setData("님은 로그인 상태입니다.");
        } else {
            response.setSuccess(false);
            response.setData("로그인 상태가 아닙니다.");
        }
        return response;
    }

//    @GetMapping("/count/post")
//    public List<HotUserDTO> getAllUsersPostCount() {
//        return userService.getAllUsersPostCount();
//    }

}

