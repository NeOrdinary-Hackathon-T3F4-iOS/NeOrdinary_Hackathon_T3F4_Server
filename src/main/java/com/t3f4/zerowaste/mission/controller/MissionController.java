package com.t3f4.zerowaste.mission.controller;

import com.t3f4.zerowaste.mission.dto.MemberMissionResponse;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/")
    public ResponseEntity<List<MemberMissionResponse>> getMissions(@RequestParam("uuId") String userUuid) {
        List<MemberMissionResponse> response =  missionService.getMissions(userUuid);
        return ResponseEntity.ok(response);
    }
  
    @GetMapping("/{id}")
    public MissionStatDto getMissionStatus(@PathVariable("id") Long id,
                                           @RequestParam("uuId") String authorization) {
        if (authorization == null || authorization.isEmpty() || id == null)
            throw new NullPointerException("Authorization header is empty");
        return missionService.getMissionStat(id, authorization);
    }
}
