package com.example.back.controller;

<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> d8638e6a3e5bebbb77aceea82d7585cd14795014
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
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
}
