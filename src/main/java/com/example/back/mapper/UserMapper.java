package com.example.back.mapper;

//import com.example.back.dto.HotUserDTO;
import com.example.back.dto.UserDTO;
import com.example.back.dto.UserPostCountDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {
    void signup(UserDTO user);

    boolean emailExists(@Param("email") String email);

    boolean nicknameExists(@Param("nickname") String nickname);


    String findIdByEmail(@Param("email") String email);

    boolean authenticateUser(UserDTO user);

    UserDTO findById(int id);

    UserDTO findByEmail(String email);

    List<UserPostCountDTO> getAllUsersPostCount();

    void changeNickname(String nickname, int id);

    void changePassword(String password, int id);

}


