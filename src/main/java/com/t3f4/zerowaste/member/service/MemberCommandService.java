package com.t3f4.zerowaste.member.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import com.t3f4.zerowaste.avatar.domain.MemberAvatar;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.repository.AvatarRepository;
import com.t3f4.zerowaste.avatar.repository.GrothLevelRepository;
import com.t3f4.zerowaste.avatar.repository.MemberAvatarRepository;
import com.t3f4.zerowaste.member.MemberConverter;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.dto.FrontScreenDto;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import java.util.ArrayList;
import java.util.List;

import com.t3f4.zerowaste.mission.domain.RewardType;
import com.t3f4.zerowaste.mission.dto.MissionSimpleCount;
import com.t3f4.zerowaste.mission.dto.PointDto;
import com.t3f4.zerowaste.mission.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    private final MemberMissionRepository missionRepository;
    private final PointRepository pointRepository;
    private final AvatarRepository avatarRepository;
    private final MemberAvatarRepository memberAvatarRepository;
    private final GrothLevelRepository grothLevelRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public void insertMember(String memberUuid) {
        if (memberUuid == null || memberUuid.isBlank()) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        boolean isExists = memberRepository.findByUuid(memberUuid).isPresent();

        if (isExists) {
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

    public FrontScreenDto getFrontScreen(String memberUuid) {
        if (memberUuid == null || memberUuid.isBlank() || memberRepository.findByUuid(memberUuid).isEmpty())
            throw new GeneralException(ErrorStatus._MEMBER_NOT_FOUND);
        List<MissionSimpleCount> totalMission = missionRepository.findTotalMissionCountByPeriod(memberUuid);
        System.out.println(totalMission);
        List<MissionSimpleCount> completedMission = missionRepository.findCompletedMissionCountByPeriod(memberUuid);
        System.out.println(completedMission);
//        List<PointDto> pointCollected = pointRepository.findPointByUuid(memberUuid);
//        System.out.println(pointCollected);
        List<PointDto> pointUsed = pointRepository.findPointUseByUuid(memberUuid);
        System.out.println(pointUsed);
        Optional<MemberAvatar> memberAvatar = memberAvatarRepository.getAvatar(memberUuid);
        if (memberAvatar.isEmpty()) throw new GeneralException(ErrorStatus._MEMBER_NOT_FOUND);
        MemberAvatar memberAvatarGet = memberAvatar.get();
        Avatar avatar = memberAvatarGet.getAvatar();
        int grothStep = determineLevelFromPoints(memberAvatarGet.getCurrentGroth());
        Optional<GrothLevel> grothLevelOptional = grothLevelRepository.findByLevelAndAvatar(grothStep, avatar);
        if (grothLevelOptional.isEmpty()) throw new GeneralException(ErrorStatus._MEMBER_NOT_FOUND);
        GrothLevel grothLevel = grothLevelOptional.get();
        Map<PeriodType, Long> currentMissionsStat = new HashMap<>();
        Map<PeriodType, Long> totalMissionsStat = new HashMap<>();
        for (MissionSimpleCount mission : totalMission)
            totalMissionsStat.put(mission.getPeriodType(), mission.getMissionCount());
        for (MissionSimpleCount mission : completedMission)
            currentMissionsStat.put(mission.getPeriodType(), mission.getMissionCount());
        Map<RewardType, Long> rewardsStat = new HashMap<>();
//        for (PointDto point : pointCollected)
//            rewardsStat.put(point.getRewardType(), point.getAmount());
//        for (PointDto point : pointUsed)
//            rewardsStat.put(point.getRewardType(), rewardsStat.getOrDefault
//                    (point.getRewardType(), 0L) - point.getAmount());
        for (PointDto point : pointUsed)
            rewardsStat.put(point.getRewardType(), point.getAmount());
        return FrontScreenDto.builder()
                .sun(rewardsStat.getOrDefault(RewardType.SUN, 0L))
                .pot(rewardsStat.getOrDefault(RewardType.POT, 0L))
                .time(rewardsStat.getOrDefault(RewardType.TIME, 0L))
                .fertilizer(rewardsStat.getOrDefault(RewardType.FERTILIZER, 0L))
                .currentGroth(memberAvatarGet.getCurrentGroth())
                .avatarData(AvatarResponse.from(avatar, grothLevel))
                .daily_total(totalMissionsStat.getOrDefault(PeriodType.DAILY, 0L))
                .daily_current(currentMissionsStat.getOrDefault(PeriodType.DAILY, 0L))
                .weekly_total(totalMissionsStat.getOrDefault(PeriodType.WEEKLY, 0L))
                .weekly_current(currentMissionsStat.getOrDefault(PeriodType.WEEKLY, 0L))
                .build();
    }

    private int determineLevelFromPoints(int points) {
        if (points >= 100) return 3;
        else if (points >= 50) return 2;
        else return 1;
    }
}