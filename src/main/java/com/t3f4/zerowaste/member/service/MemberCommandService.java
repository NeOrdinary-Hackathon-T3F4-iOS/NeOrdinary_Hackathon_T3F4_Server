package com.t3f4.zerowaste.member.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.member.MemberConverter;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public void insertMember(String memberUuid) {
        if (memberUuid == null || memberUuid.isBlank()) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        Member member = MemberConverter.mapToMember(memberUuid);
        memberRepository.save(member);

        // 1. 주간 미션 4개 조회
        List<Mission> weeklyMissions = missionRepository.findRandomMissionsByPeriodType(PeriodType.WEEKLY.name(), 4);

        // 2. 일간 미션 2개 조회
        List<Mission> dailyMissions = missionRepository.findRandomMissionsByPeriodType(PeriodType.DAILY.name(), 2);


        // 3. MemberMission 생성
        List<MemberMission> memberMissions = new ArrayList<>();
        for (Mission mission : weeklyMissions) {
            memberMissions.add(createMemberMission(member, mission));
        }
        for (Mission mission : dailyMissions) {
            memberMissions.add(createMemberMission(member, mission));
        }

        // 4. 저장
        memberMissionRepository.saveAll(memberMissions);
    }

    private MemberMission createMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
            .member(member)
            .mission(mission)
            .status(MissionStatus.NOT_STARTED) // 기본 상태 설정
            .count(0)
            .completedAt(null)
            .build();
    }
}