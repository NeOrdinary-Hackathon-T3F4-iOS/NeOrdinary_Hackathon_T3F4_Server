package com.t3f4.zerowaste.member.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.member.dto.FrontScreenDto;
import com.t3f4.zerowaste.member.service.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @Operation(
            summary = "uuid 등록",
            description = "사용자의 uuid를 등록합니다."
    )
    @PostMapping("/members/{memberUuid}")
    public ApiResponse<Void> insertMember(@PathVariable String memberUuid) {
        memberCommandService.insertMember(memberUuid);
        return ApiResponse.onSuccess(null);
    }

    @Operation(
            summary = "메인 화면",
            description = "메인 화면 데이터를 조회합니다."
    )
    @GetMapping("/main")
    public ApiResponse<FrontScreenDto> frontScreen(@RequestParam("uuid") String uuid) {
        return ApiResponse.onSuccess(memberCommandService.getFrontScreen(uuid));
    }
}