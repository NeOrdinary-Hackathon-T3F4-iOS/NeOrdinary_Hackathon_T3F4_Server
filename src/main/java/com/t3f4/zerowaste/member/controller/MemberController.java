package com.t3f4.zerowaste.member.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.member.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/{memberUuid}")
    public ApiResponse<Void> insertMember(@PathVariable String memberUuid) {
        memberCommandService.insertMember(memberUuid);
        return ApiResponse.onSuccess(null);
    }
}