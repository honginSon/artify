package com.elice.artBoard.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private Long id; // 게시글키

    private String title; // 게시글 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 게시글 내용

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성 시간

    @Column(name = "edited_at")
    private LocalDateTime editedAt; // 수정 시간

    private int memberId; // 회원키(외래키)
    private int boardId; // 게시판키(외래키)

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<PostImage> postImages;  // 해당 게시글에 속한 이미지 리스트


    // 엔티티가 처음 저장되기 전 호출 (생성 시간 설정)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 엔티티가 업데이트되기 전 호출 (수정 시간 설정)
    @PreUpdate
    protected void onUpdate() {
        this.editedAt = LocalDateTime.now();
    }

    private Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Post create(String title, String content) {
        return new Post(title, content);
    }

    public Post update(String title, String content) {
        this.title = title;
        this.content = content;

        return this;
    }

}
