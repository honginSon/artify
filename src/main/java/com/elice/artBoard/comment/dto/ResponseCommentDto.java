package com.elice.artBoard.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseCommentDto {

    private Long commentId;
    private Integer memberId;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
}
