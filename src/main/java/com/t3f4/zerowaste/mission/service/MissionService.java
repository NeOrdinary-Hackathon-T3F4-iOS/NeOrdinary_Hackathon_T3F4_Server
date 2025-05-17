package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.dto.MemberMissionResponse;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public List<MemberMissionResponse> getMissions(String userUuid) {
        List<MemberMission> memberMissions = memberMissionRepository.findByMemberUuidWithMission(userUuid);

        return memberMissions.stream()
                .map(MemberMissionResponse::from)
                .toList();
    }
}
