package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.dto.MissionResponse;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public List<MissionResponse> getMissions(String userUuid) {
        Member member = (Member) memberRepository.findByUuid(userUuid)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        List<Mission> missions = missionRepository.findByMember(member);
        return missions.stream()
                .map(MissionResponse::from)
                .toList();
    }
}
