package com.elice.artBoard.member.mapper;

import com.elice.artBoard.member.entity.Member;
import com.elice.artBoard.member.entity.MemberPostDto;
import com.elice.artBoard.member.entity.MemberResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member MemberPostDtoToMember(MemberPostDto memberPostDto);

    MemberResponseDto MemberToMemberResponseDto(Member member);
}
