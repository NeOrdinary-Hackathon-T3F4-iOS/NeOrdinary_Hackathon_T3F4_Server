package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.exception.UnauthorizedException;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.dto.MissionStatRepoDto;
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
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionStatDto getMissionStat(long memberMissionId, String memberName) {
        List<MissionStatRepoDto> queryResult = memberMissionRepository.findByIdInDto(memberMissionId);
        if (queryResult.isEmpty())
            throw new NoSuchElementException("No mission stat found with id " + memberMissionId);
        MissionStatRepoDto firstMissionStat = queryResult.get(0);
        if (!memberName.equals(firstMissionStat.getMemberId()))
            throw new UnauthorizedException("Member " + memberName + " is not authorized to view mission " + memberMissionId);
        return MissionStatDto.builder()
                .title(firstMissionStat.getTitle())
                .content(firstMissionStat.getContent())
                .count(firstMissionStat.getCount())
                .reward(firstMissionStat.getReward())
                .deadline(firstMissionStat.getDeadline())
                .periodType(firstMissionStat.getPeriodType().toString())
                .imageUrl(queryResult.stream().map(MissionStatRepoDto::getImageUrl)
                        .filter(Objects::nonNull).toList())
                .build();
    }

    public List<MemberMissionResponse> getMissions(String userUuid) {
        List<MemberMission> memberMissions = memberMissionRepository.findByMemberUuidWithMission(userUuid);

        return memberMissions.stream()
                .map(MemberMissionResponse::from)
                .toList();
    }
}