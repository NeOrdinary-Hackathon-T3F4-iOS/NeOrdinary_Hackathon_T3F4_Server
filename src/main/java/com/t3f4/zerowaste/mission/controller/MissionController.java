package com.t3f4.zerowaste.mission.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.mission.dto.MemberMissionResponse;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.service.MissionCommandService;
import com.t3f4.zerowaste.mission.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;
    private final MissionCommandService missionCommandService;

    @Operation(
            summary = "미션 조회",
            description = "미션 목록을 조회합니다."
    )
    @GetMapping("/")
    public ApiResponse<List<MemberMissionResponse>> getMissions(@RequestParam("uuid") String userUuid) {
        List<MemberMissionResponse> response =  missionService.getMissions(userUuid);
        return ApiResponse.onSuccess(response);
    }

    @Operation(
            summary = "미션 수행 상태 보기",
            description = "미션 수행 상태를 조회합니다."
    )
    @GetMapping("/{id}")
    public ApiResponse<MissionStatDto> getMissionStatus(@PathVariable("id") Long id,
                                                        @RequestParam("uuid") String uuid) {
        if (uuid == null || uuid.isEmpty() || id == null)
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        return ApiResponse.onSuccess(missionService.getMissionStat(id, uuid));
    }

    @Operation(
            summary = "미션 인증",
            description = "미션 인증 사진을 업로드합니다."
    )
    @PostMapping("/{memberMissionId}/progress/{memberUuid}")
    public ApiResponse<Void> uploadMissionImage(
        @PathVariable Long memberMissionId,
        @PathVariable String memberUuid,
        @RequestPart("file") MultipartFile file
    ) {
        missionCommandService.uploadMissionImage(memberMissionId, memberUuid, file);
        return ApiResponse.onSuccess(null);
    }

    @Operation(summary = "미션 보상 지급", description = "GET_REWARDS 상태인 미션들을 COMPLETED로 변경하고 보상 아이템을 지급합니다.")
    @PostMapping("/missions/rewards")
    public ResponseEntity<ApiResponse<String>> rewardMissions(
            @RequestParam String uuid,
            @RequestParam Long memberMissionId
    ) {
        missionService.completeMissionAndGiveReward(uuid, memberMissionId);
        return ResponseEntity.ok(ApiResponse.onSuccess("보상이 지급되었습니다."));
    }
}