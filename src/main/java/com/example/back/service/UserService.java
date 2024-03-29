package com.example.back.service;

import com.example.back.configuration.PasswordEncryption;
//import com.example.back.dto.HotUserDTO;
import com.example.back.dto.UserDTO;
import com.example.back.dto.UserPostCountDTO;
import com.example.back.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public void insertUserData(UserDTO user) {
        String hashedPassword = PasswordEncryption.hashPassword(user.getEmail(), user.getPassword());
        user.setPassword(hashedPassword);
        mapper.signup(user);
    }

    public boolean isEmailDuplicated(String email) {
        return mapper.emailExists(email);
    }

    public boolean isNicknameDuplicated(String nickname) {
        return mapper.nicknameExists(nickname);
    }

    public String findIdByEmail(String email) {
        return mapper.findIdByEmail(email);
    }

    public boolean authenticateUser(UserDTO user) {
        String hashedPassword = PasswordEncryption.hashPassword(user.getEmail(), user.getPassword());
        user.setPassword(hashedPassword);
        return mapper.authenticateUser(user);
    }

    public UserDTO getUserById(int id) {
        return mapper.findById(id);
    }

    public UserDTO login(String email, String password) {
        UserDTO user = mapper.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public List<UserPostCountDTO> getAllUsersPostCount() {
        return mapper.getAllUsersPostCount();
    }

    public void changeNickname (String nickname, int id) {
        mapper.changeNickname(nickname, id);
    }

    public void changePassword (String password, int id) {
        mapper.changePassword(password, id);
    }

}
