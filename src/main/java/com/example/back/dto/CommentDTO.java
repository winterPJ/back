package com.example.back.dto;

import com.example.back.utils.Datetime;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private int id;

    private int post_id;

    private int user_id;

    private String body;

    private LocalDateTime created_at;

    public CommentDTO() {
        this.created_at = Datetime.nowInKst();
    }
}
