package com.t3f4.zerowaste.avatar.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarCreateRequest;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.service.AvatarService;
import com.t3f4.zerowaste.avatar.service.PointUseService;
import com.t3f4.zerowaste.mission.domain.RewardType;
import jdk.jfr.Description;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;
    private final PointUseService pointUseService;

    @Description("현재 소유하고 있는 아이템의 개수를 셉니다.")
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> getUseCount(
            @RequestParam String uuid,
            @RequestParam RewardType rewardType
    ) {
        int count = pointUseService.getUseCount(uuid, rewardType);
        Map<String, Integer> result = Map.of("count", count);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AvatarResponse>> createAvatar(
            @RequestParam String uuid,
            @RequestBody AvatarCreateRequest request
    ) {
        AvatarResponse response = avatarService.createAvatar(uuid, request);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}
