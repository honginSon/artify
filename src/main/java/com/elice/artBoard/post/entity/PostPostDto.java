package com.elice.artBoard.post.entity;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPostDto {
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    // Post 엔티티로 변환하는 메서드
    public Post toPost(int member_id, int board_id) {
        return Post.builder()
                .title(title)
                .content(content)
                .member_id(member_id)
                .board_id(board_id)
                .build();
    }

    /* 빌더를 사용하면 좋을지
    - 빌더는 불변 객체를 생성할 때 많이 사용한다고, 하지만 제목과 내용은 가변임
    - 빌더랑 세터를 같이 쓰면 안좋다는데 어떻게 할지
    public Post toPost(int memberId, int boardId) {
        return Post.builder(title, content) // 제목과 내용을 매개변수로 전달
                .memberId(memberId)
                .boardId(boardId)
                .build();
    }*/
}
