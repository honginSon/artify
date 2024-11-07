package com.elice.artBoard.member.service;

import com.elice.artBoard.member.repository.MemberRepository;
import com.elice.artBoard.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    // 전체 회원 목록
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Member findMember(Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));
    }

    // DB에 로그인 할 회원이 있는지 조회
    public Member checkMember(Member member) {
        return memberRepository.check(member)
                .orElseThrow(() -> new RuntimeException("로그인 할 수 없습니다."));
    }

    // 새로운 회원 저장
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // 기존 회원 프로필 수정
    public Member updateMember(Member member) {
        Member findMember = memberRepository.findById(member.getMemberId())
                .orElseThrow(() -> new RuntimeException("수정할 회원이 없습니다."));

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getEmail())
                .ifPresent(email -> findMember.setEmail(email));
        Optional.ofNullable(member.getPassword())
                .ifPresent(password -> findMember.setPassword(password));
        Optional.ofNullable(member.getNickname())
                .ifPresent(nickname -> findMember.setNickname(nickname));

        return memberRepository.save(findMember);
    }

    // 회원 탈퇴 -> 정말 탈퇴할 것인지 다시 묻기
    public void deleteMember(Integer memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("삭제할 회원이 없습니다."));

        memberRepository.delete(findMember);
    }
}
