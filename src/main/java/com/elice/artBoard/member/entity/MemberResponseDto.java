package com.elice.artBoard.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDto {
    private int memberId;
    private String name;
    private String email;
    private String nickname;

    // 회원 생성, 수정 시간 ?
}
