package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.avatar.domain.PointUse;
import com.t3f4.zerowaste.avatar.repository.PointUseRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.RewardType;
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
    private final MemberRepository memberRepository;
    private final PointUseRepository pointUseRepository;

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

    @Transactional
    public void completeMissionAndGiveReward(String memberUuid, Long memberMissionId) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_MISSION_NOT_FOUND));

        if (!memberMission.getMember().equals(member)) {
            throw new GeneralException(ErrorStatus._UNAUTHORIZED_ACCESS);
        }

        if (memberMission.getStatus() != MissionStatus.GET_REWARDS) {
            throw new GeneralException(ErrorStatus._INVALID_MISSION_STATUS);
        }

        // 상태 변경
        memberMission.updateMissionStatus(MissionStatus.COMPLETED);

        // 보상 지급
        RewardType rewardType = memberMission.getMission().getReward();
        PointUse pointUse = PointUse.builder()
                .member(member)
                .rewardType(rewardType)
                .build();
        pointUseRepository.save(pointUse);
    }
}