package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.mission.dto.ImageDto;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.dto.MissionStatRepoDto;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.dto.MemberMissionResponse;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionStatDto getMissionStat(long memberMissionId, String memberName) {
        List<MissionStatRepoDto> queryResult = memberMissionRepository.findByIdInDto(memberMissionId);
        if (queryResult.isEmpty())
            throw new GeneralException(ErrorStatus._MISSION_NOT_FOUND);
        log.info(queryResult.toString());
        MissionStatRepoDto firstMissionStat = queryResult.get(0);
        if (!memberName.equals(firstMissionStat.getUuid()))
            throw new GeneralException(ErrorStatus._MISSION_MEMBER_NOT_MATCH);
        return MissionStatDto.builder()
                .title(firstMissionStat.getTitle())
                .content(firstMissionStat.getContent())
                .count_reward(firstMissionStat.getCount_reward())
                .count_current(firstMissionStat.getCount_current())
                .reward(firstMissionStat.getReward().toString())
                .periodType(firstMissionStat.getPeriodType().toString())
                .images(queryResult.stream()
                        .filter(i -> i.getCreatedAt() != null && i.getImageUrl() != null)
                        .map(q -> ImageDto.builder()
                                .imageUrl(q.getImageUrl())
                                .date(q.getCreatedAt().toLocalDate())
                                .build())
                        .toList())
                .build();
    }

    public List<MemberMissionResponse> getMissions(String userUuid) {
        List<MemberMission> memberMissions = memberMissionRepository.findByMemberUuidWithMission(userUuid);
        if (memberMissions.isEmpty()) throw new GeneralException(ErrorStatus._MEMBER_NOT_FOUND);
        return memberMissions.stream()
                .map(MemberMissionResponse::from)
                .toList();
    }
}