package com.t3f4.zerowaste.avatar.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.avatar.dto.GrothLevelResponse;
import com.t3f4.zerowaste.avatar.service.AvatarService;
import com.t3f4.zerowaste.avatar.service.PointUseService;
import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/growth")
@RequiredArgsConstructor
public class GrowthLevelController {

    private final AvatarService avatarService;
    private final PointUseService pointUseService;

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<GrothLevelResponse>> updateGrowthLevel(
            @RequestParam String uuid,
            @RequestParam Long avatarId,
            @RequestParam RewardType rewardType
    ) {
        // 1. 아이템 사용 (PointUse에서 1개 차감)
        pointUseService.useReward(uuid, rewardType);

        // 2. 성장 레벨 업데이트 (포인트 누적)
        GrothLevelResponse response = avatarService.updateGrowthLevel(uuid, avatarId, rewardType);

        // 3. 결과 반환
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }
}
