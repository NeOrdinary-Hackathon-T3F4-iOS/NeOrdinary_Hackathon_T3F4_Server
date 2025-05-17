package com.t3f4.zerowaste.mission.controller;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.dto.MissionResponse;
import com.t3f4.zerowaste.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/")
    public ResponseEntity<List<MissionResponse>> getMissions(@RequestParam("uuId") String userUuid) {
        List<MissionResponse> response =  missionService.getMissions(userUuid);
        return ResponseEntity.ok(response);
    }
}
