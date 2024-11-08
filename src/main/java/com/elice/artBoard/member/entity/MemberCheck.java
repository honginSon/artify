package com.elice.artBoard.member.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCheck {
    private String name;
    private String email;
    private String nickname;
    private String objectName;

    public MemberCheck(MemberPostDto memberPostDto) {
        this.name = memberPostDto.getName();
        this.email = memberPostDto.getEmail();
        this.nickname = memberPostDto.getNickname();
        this.objectName = "memberCreate";
    }
}
