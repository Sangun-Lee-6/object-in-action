package com.example.spring_playground.controller;

import com.example.spring_playground.domain.Member;
import com.example.spring_playground.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@ModelAttribute Member member) {
        memberService.join(member);
        return "redirect:/members";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
