package com.t3f4.zerowaste;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.controller.MissionController;
import com.t3f4.zerowaste.mission.domain.*;
import com.t3f4.zerowaste.mission.dto.MissionStatDto;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import com.t3f4.zerowaste.mission.repository.PhotoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MissionTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private MemberMissionRepository missionMissionRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private MissionController missionController;


//    @Test
//    @DisplayName("일반적인 테스트")
//    @Transactional
//    void missionStatusTest1() {
//        Member member = Member.builder()
//                .name(UUID.randomUUID().toString())
//                .status("active")
//                .build();
//        memberRepository.save(member);
//        Mission mission = Mission.builder()
//                .title("테스트 미션 1")
//                .content("테스트 미션 1입니다.")
//                .count(2)
//                .reward(RewardType.POT)
//                .periodType(PeriodType.DAILY)
//                .build();
//        missionRepository.save(mission);
//        MemberMission memberMission = MemberMission.builder()
//                .mission(mission)
//                .member(member)
//                .status(MissionStatus.NOT_STARTED)
//                .count(1)
//                .build();
//        Photo photo = Photo.builder()
//                .imageUrl("대충 url")
//                .memberMission(memberMission)
//                .build();
//        memberMission.setPhotos(List.of(photo));
//        missionMissionRepository.save(memberMission);
//        photoRepository.save(photo);
//
//        MissionStatDto result = missionController.getMissionStatus(memberMission.getId(),
//                member.getName()).getResult();
//        System.out.println(result);
//        assertEquals(mission.getTitle(), result.getTitle());
//        assertEquals(mission.getContent(), result.getContent());
//        assertEquals(mission.getCount(), result.getCount());
//        assertEquals(mission.getReward().toString(), result.getReward());
//        assertEquals(mission.getPeriodType().toString(), result.getPeriodType());
//        assertEquals(1, result.getImageUrl().size());
//        assertEquals(photo.getImageUrl(), result.getImageUrl().get(0));
//    }
//
//    @Test
//    @DisplayName("사진 없을 때의 테스트")
//    @Transactional
//    void missionStatusTest2() {
//        Member member = Member.builder()
//                .name(UUID.randomUUID().toString())
//                .status("active")
//                .build();
//        memberRepository.save(member);
//        Mission mission = Mission.builder()
//                .title("테스트 미션 2")
//                .content("테스트 미션 2입니다.")
//                .count(1)
//                .reward(RewardType.FERTILIZER)
//                .periodType(PeriodType.DAILY)
//                .build();
//        missionRepository.save(mission);
//        MemberMission memberMission = MemberMission.builder()
//                .mission(mission)
//                .member(member)
//                .status(MissionStatus.NOT_STARTED)
//                .count(0)
//                .photos(List.of())
//                .build();
//        missionMissionRepository.save(memberMission);
//
//        MissionStatDto result = missionController.getMissionStatus(memberMission.getId(),
//                member.getName()).getResult();
//        System.out.println(result);
//        assertEquals(mission.getTitle(), result.getTitle());
//        assertEquals(mission.getContent(), result.getContent());
//        assertEquals(mission.getCount(), result.getCount());
//        assertEquals(mission.getReward().toString(), result.getReward());
//        assertEquals(mission.getPeriodType().toString(), result.getPeriodType());
//        assertEquals(0, result.getImageUrl().size());
//    }


//    @Test
//    @DisplayName("다른 사용자 것을 요청함")
//    @Transactional
//    void missionStatusTest3() {
//        Member member1 = Member.builder()
//                .name(UUID.randomUUID().toString())
//                .status("active")
//                .build();
//        Member member2 = Member.builder()
//                .name(UUID.randomUUID().toString())
//                .status("active")
//                .build();
//        memberRepository.save(member1);
//        Mission mission = Mission.builder()
//                .title("테스트 미션 3")
//                .content("테스트 미션 3입니다.")
//                .count(2)
//                .reward(RewardType.POT)
//                .periodType(PeriodType.DAILY)
//                .build();
//        missionRepository.save(mission);
//        MemberMission memberMission = MemberMission.builder()
//                .mission(mission)
//                .member(member1)
//                .status(MissionStatus.NOT_STARTED)
//                .count(1)
//                .build();
//        Photo photo = Photo.builder()
//                .imageUrl("대충 url")
//                .memberMission(memberMission)
//                .build();
//        memberMission.setPhotos(List.of(photo));
//        missionMissionRepository.save(memberMission);
//        photoRepository.save(photo);
//
//        assertThrows(UnauthorizedException.class, () ->
//                missionController.getMissionStatus(memberMission.getId(),
//                        member2.getName()));
//    }
//
//
//    @Test
//    @DisplayName("각종 터짐 테스트")
//    @Transactional
//    void missionStatusTest4() {
//        assertThrows(NullPointerException.class, () ->
//                missionController.getMissionStatus(null, null));
//        assertThrows(NoSuchElementException.class, () ->
//                missionController.getMissionStatus(-2L, UUID.randomUUID().toString()));
//    }
}