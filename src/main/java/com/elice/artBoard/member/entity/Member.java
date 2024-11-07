package com.elice.artBoard.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL : Auto increment
    private Integer memberId;
    private String name;
    private String email;
    private String nickname;
    private String password;
    // 회원 생성, 수정 시간 / BaseEntity 사용 ?
}
