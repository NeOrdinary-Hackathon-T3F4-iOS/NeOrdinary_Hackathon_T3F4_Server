package com.t3f4.zerowaste.member.controller;

import com.t3f4.zerowaste.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
}