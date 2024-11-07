package com.elice.artBoard.post.entity;

import jakarta.persistence.*;
import lombok.*;

import static com.elice.artBoard.post.constants.DefaultImgConst.DEFAULT_IMG_PATH;
import static com.elice.artBoard.post.constants.DefaultImgConst.DEFAULT_IMAGE_NAME;

@Entity
@Getter
@Setter
@Table(name = "post_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImage {
    @Id
    @GeneratedValue
    @Column(name = "board_image_id")
    private Long id;

    @Column(name = "post_image_name", nullable = false)
    private String imageName;

    @Column(name = "post_image_path", nullable = false)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private PostImage(String imageName, String imagePath, Post post) {
        this.imageName = imageName;
        this.imagePath = imagePath;
        this.post = post;
    }

    public static PostImage create(String imageName, String imagePath, Post post) {
        return new PostImage(imageName, imagePath, post);
    }

    public static PostImage createDefaultImage(Post post) {
        return new PostImage(DEFAULT_IMG_PATH, DEFAULT_IMAGE_NAME, post);
    }

}
