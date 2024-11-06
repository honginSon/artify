package com.elice.artBoard.member.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class MemberPostDto {
    private String name;
    private String email;
    private String nickname;
    private String password;
    // 회원 생성, 수정 시간 ?

    // 로그인 데이터를 받는 객체
    public MemberPostDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
