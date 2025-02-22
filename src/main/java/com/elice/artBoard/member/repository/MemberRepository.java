package com.elice.artBoard.member.repository;

import com.elice.artBoard.member.entity.Member;
import com.elice.artBoard.member.entity.MemberPostDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<Member> findAll();

    Optional<Member> findById(int memberId);

    Optional<Member> findByEmail(String email);

    Optional<Member> check(Member member);

    Member save(Member member);

    void delete(Member member);
}
