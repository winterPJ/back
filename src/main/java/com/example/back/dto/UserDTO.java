package com.example.back.dto;

import com.example.back.utils.Datetime;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String passwordConfirm;
    private String nickname;
    private LocalDateTime created_at;
    private int post_cnt = 0;
    private int state = 0;
    private String profile_img;

    private int comment_cnt = 0;

    public UserDTO() {
        this.created_at = Datetime.nowInKst();
        this.profile_img = "https://upload.wikimedia.org/wikipedia/commons/2/2c/Default_pfp.svg";
    }

}
