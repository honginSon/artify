package com.elice.artBoard.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id; // 게시글키

    private String title; // 게시글 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 게시글 내용

    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at; // 생성 시간

    @Column(name = "edited_at")
    private LocalDateTime edited_at; // 수정 시간

    private int member_id; // 회원키(외래키)
    private int board_id; // 게시판키(외래키)

    // 엔티티가 처음 저장되기 전 호출 (생성 시간 설정)
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

    // 엔티티가 업데이트되기 전 호출 (수정 시간 설정)
    @PreUpdate
    protected void onUpdate() {
        this.edited_at = LocalDateTime.now();
    }

}
