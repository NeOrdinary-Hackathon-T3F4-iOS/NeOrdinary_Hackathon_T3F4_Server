package com.t3f4.zerowaste.mission.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.mission.dto.MemberMissionResponse;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/")
    public ApiResponse<List<MemberMissionResponse>> getMissions(@RequestParam("uuId") String userUuid) {
        List<MemberMissionResponse> response =  missionService.getMissions(userUuid);
        return ApiResponse.onSuccess(response);
    }
  
    @GetMapping("/{id}")
    public ApiResponse<MissionStatDto> getMissionStatus(@PathVariable("id") Long id,
                                                        @RequestParam("uuid") String uuid) {
        if (uuid == null || uuid.isEmpty() || id == null)
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        return ApiResponse.onSuccess(missionService.getMissionStat(id, uuid));
    }
}