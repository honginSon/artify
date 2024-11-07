package com.elice.artBoard.post.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPostDto {
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    private MultipartFile image;


}
