package com.example.back.controller;

import com.example.back.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/user/daewon")
    public Map<String, String> getUser() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "임대원");
        response.put("age", "25");
        response.put("job", "개발자");
        return response;
    }

    @GetMapping("/test/session")
    public int getSession(@SessionAttribute(name = "user", required = false) UserDTO user) {
        int result = user.getId();
        return result;
    }


}

