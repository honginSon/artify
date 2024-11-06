package com.elice.artBoard.post.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private int post_id; // 게시글키
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private LocalDateTime created_at; // 생성 시간
    private LocalDateTime edited_at; // 수정 시간
    private int member_id; // 회원키(외래키)
    private int board_id; // 게시판키(외래키)
}
