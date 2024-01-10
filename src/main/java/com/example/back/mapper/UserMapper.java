package com.example.back.mapper;

import com.example.back.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    void signup(UserDTO user);

    boolean emailExists(@Param("email") String email);

    boolean nicknameExists(@Param("nickname") String nickname);


    String findIdByEmail(@Param("email") String email);

    boolean authenticateUser(UserDTO user);

}
