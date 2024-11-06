package com.elice.artBoard.member.controller;

import com.elice.artBoard.member.mapper.MemberMapper;
import com.elice.artBoard.member.service.MemberService;
import com.elice.artBoard.member.entity.Member;
import com.elice.artBoard.member.entity.MemberPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    // 초기 화면 주소
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(Model model, MemberPostDto memberPostDto) {
        Member result = memberService.checkMember(memberPostDto);

        if (result == null) {
            // 이메일 또는 패스워드가 틀립니다
            // 아직은 500 에러
        }

        model.addAttribute("loginMember", result);

        // 이 부분은 로그인이 잘 되는지 확인
        return "board/list";
    }

    // 회원 가입 화면
    @GetMapping("/create")
    public String create() {
        return "member/create";
    }

    // 회원 가입 처리
    @PostMapping("/create")
    public String create(Model model, MemberPostDto memberPostDto) {
        Member member = memberMapper.MemberPostDtoToMember(memberPostDto);

        Member newMember = memberService.createMember(member);
        model.addAttribute("newMember", newMember);

        return "redirect:/login";
    }

/* Board 목록 보여주는 파일 받고 다시 개발
    @GetMapping("/{memberId}")
    public String profile() {
        return "member/detail";
    }

    @GetMapping("/{memberId}")
    public String update(@PathVariable Integer id) {
        return "member/update";
    }

    @PutMapping("/{memberId}")
    public String update(@PathVariable Integer id, MemberPostDto memberPostDto) {
        Member member = memberMapper.MemberPostDtoToMember(memberPostDto);
        memberService.updateMember(member);

        return "redirect:/member/detail";
    }
    */

    @GetMapping("/board")
    public String list() {
        return "board/list";
    }
}
