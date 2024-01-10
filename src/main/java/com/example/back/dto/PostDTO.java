package com.example.back.dto;

import com.example.back.utils.Datetime;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class PostDTO {
    private int id;
    private int user_id;
    private String title;
    private String body;
    private int comment_cnt;
    private LocalDateTime created_at;

    public PostDTO() {
        this.created_at = Datetime.nowInKst();
    }
}
