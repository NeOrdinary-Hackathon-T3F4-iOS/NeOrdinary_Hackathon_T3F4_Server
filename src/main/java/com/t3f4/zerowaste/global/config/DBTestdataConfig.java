package com.t3f4.zerowaste.global.config;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import com.t3f4.zerowaste.avatar.domain.PointUse;
import com.t3f4.zerowaste.avatar.repository.AvatarRepository;
import com.t3f4.zerowaste.avatar.repository.GrothLevelRepository;
import com.t3f4.zerowaste.avatar.repository.PointUseRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.*;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import com.t3f4.zerowaste.mission.repository.PhotoRepository;
import org.springframework.stereotype.Component;
import com.t3f4.zerowaste.avatar.domain.AvatarType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class DBTestdataConfig {
    DBTestdataConfig(MemberRepository memberRepository,
                     MissionRepository missionRepository,
                     MemberMissionRepository memberMissionRepository,
                     PhotoRepository photoRepository,
                     AvatarRepository avatarRepository,
                     GrothLevelRepository grothLevelRepository,
                     PointUseRepository pointUseRepository) {
        // Member 생성
        Member member = Member.builder()
                .uuid(UUID.randomUUID().toString())
                .build();
        member = memberRepository.save(member);
        System.out.println("Saved Member UUID: " + member.getUuid());

        // Mission 1 생성
        Mission mission1 = Mission.builder()
                .title("아침 운동하기")
                .count(3)
                .content("매일 아침 스트레칭 및 운동 수행")
                .reward(RewardType.POT)
                .periodType(PeriodType.DAILY)
                .build();
        mission1 = missionRepository.save(mission1);

        // Mission 2 생성
        Mission mission2 = Mission.builder()
                .title("물 마시기")
                .count(2)
                .content("하루에 2번 물 마시기")
                .reward(RewardType.SUN)
                .periodType(PeriodType.WEEKLY)
                .build();
        mission2 = missionRepository.save(mission2);

        // MemberMission 1 생성
        MemberMission memberMission1 = MemberMission.builder()
                .member(member)
                .mission(mission1)
                .status(MissionStatus.NOT_STARTED)
                .count(0)
                .completedAt(null)
                .build();
        memberMission1 = memberMissionRepository.save(memberMission1);

        // MemberMission 2 생성
        MemberMission memberMission2 = MemberMission.builder()
                .member(member)
                .mission(mission2)
                .status(MissionStatus.COMPLETED)
                .count(2)
                .completedAt(LocalDateTime.now().minusDays(1))
                .build();
        memberMission2 = memberMissionRepository.save(memberMission2);

        // Photo 추가 (mission2에 대해 업로드된 사진)
        Photo photo1 = Photo.builder()
                .imageUrl("https://example.com/photo1.jpg")
                .memberMission(memberMission2)
                .build();
//        photo1.setUpdatedAt(LocalDateTime.now());
//        photo1.setCreatedAt(LocalDateTime.now());
        Photo photo2 = Photo.builder()
                .imageUrl("https://example.com/photo2.jpg")
                .memberMission(memberMission2)
                .build();
//        photo2.setUpdatedAt(LocalDateTime.now());
//        photo2.setCreatedAt(LocalDateTime.now());
        photoRepository.saveAllAndFlush(List.of(photo1, photo2));

        // Avatar 생성
        Avatar avatar = Avatar.builder()
                .realName("리얼네임")
                .avatarType(AvatarType.SHAMPO) // enum 값 사용
                .build();
        avatar = avatarRepository.save(avatar);
        // GrothLevel 생성
        GrothLevel grothLevel = GrothLevel.builder()
                .level(1)
                .requirement(0)
                .label("새싹")
                .avatar(avatar)
                .build();
        grothLevelRepository.save(grothLevel);

        // PointUse 더미 데이터 생성 (2개)
        PointUse pointUse1 = PointUse.builder()
                .member(member)
                .rewardType(RewardType.SUN)
                .build();

        PointUse pointUse2 = PointUse.builder()
                .member(member)
                .rewardType(RewardType.SUN)
                .build();
        pointUseRepository.saveAll(List.of(pointUse1, pointUse2));
        System.out.println("Saved PointUse 1 ID: " + pointUse1.getId());
        System.out.println("Saved PointUse 2 ID: " + pointUse2.getId());
        System.out.println("Saved Avatar ID: " + avatar.getId());
        System.out.println("Saved GrothLevel ID: " + grothLevel.getId());
        System.out.println("Saved MemberMission1 ID: " + memberMission1.getId());
        System.out.println("Saved MemberMission2 ID: " + memberMission2.getId());
    }
}