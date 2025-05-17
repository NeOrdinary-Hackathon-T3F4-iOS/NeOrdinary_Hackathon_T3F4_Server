package com.t3f4.zerowaste.avatar.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarCreateRequest;
//import com.t3f4.zerowaste.avatar.dto.AvatarHistoryResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.service.AvatarService;
import com.t3f4.zerowaste.avatar.service.PointUseService;
import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;
    private final PointUseService pointUseService;

    @PostMapping
    public ResponseEntity<ApiResponse<AvatarResponse>> createAvatar(
            @RequestParam String uuid,
            @RequestBody AvatarCreateRequest request
    ) {
        AvatarResponse response = avatarService.createAvatar(uuid, request);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> getUseCount(
            @RequestParam String uuid,
            @RequestParam RewardType rewardType
    ) {
        int count = pointUseService.getUseCount(uuid, rewardType);
        Map<String, Integer> result = Map.of("count", count);
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }
}

//    @GetMapping("/history")
//    public ResponseEntity<ApiResponse<List<AvatarHistoryResponse>>> getAvatarHistory(
//            @RequestParam String uuid
//    ) {
//        List<AvatarHistoryResponse> history = avatarService.getAvatarHistory(uuid);
//        return ResponseEntity.ok(ApiResponse.onSuccess(history));
//    }
//
//    @GetMapping("/{avatarId}")
//    public ResponseEntity<String> getAvatarImage(
//            @RequestParam String uuid,
//            @PathVariable Long avatarId
//    ) {
//        String imageUrl = avatarService.getAvatarImage(uuid, avatarId);
//        return ResponseEntity.ok(imageUrl);
//    }
