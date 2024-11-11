package com.elice.artBoard.comment.domain;

import com.elice.artBoard.common.entity.BaseEntity;
import com.elice.artBoard.member.entity.Member;
import com.elice.artBoard.post.entity.Post;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "commend_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    public Member member;


    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public static Comment create(Post post, String content) {
        return new Comment(content, post);
    }

    public void update(String content) {
        this.content = content;
    }
}
