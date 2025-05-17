package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.global.service.S3Service;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.domain.Photo;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import com.t3f4.zerowaste.mission.repository.PhotoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
@RequiredArgsConstructor
public class MissionCommandService {

    private final S3Service s3Service;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final PhotoRepository photoRepository;
    private final MissionRepository missionRepository;

    public void uploadMissionImage(
        Long memberMissionId,
        String memberUuid,
        MultipartFile file
    ) {
        if (memberMissionId == null || memberUuid == null) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        MemberMission findMemberMission = memberMissionRepository.findById(memberMissionId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        Mission findMission = missionRepository.findById(findMemberMission.getMission().getId())
            .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        MissionStatus missionStatus = findMemberMission.getStatus();

        if (missionStatus.equals(MissionStatus.GET_REWARDS) || missionStatus.equals(MissionStatus.COMPLETED)) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        String imageUrl = s3Service.upload(file);

        Photo photo = Photo.builder()
            .memberMission(findMemberMission)
            .imageUrl(imageUrl)
            .build();

        photoRepository.save(photo);

        findMemberMission.incrementCount();

        // 미션 성공 처리
        if (findMemberMission.getCount() >= findMission.getCount()) {
            findMemberMission.updateMissionStatus(MissionStatus.GET_REWARDS);
            findMemberMission.updateMissionCompletedAt(LocalDateTime.now());
        }
    }

    @Transactional
    public void assignMissionsByBatch(PeriodType periodType, int countPerMember) {
        int page = 0;
        int size = 1000;
        Page<Member> members;

        // 미리 전체 미션 목록 캐싱
        List<Mission> allMissions = missionRepository.findByPeriodType(periodType);

        do {
            members = memberRepository.findAll(PageRequest.of(page++, size));
            if (!members.hasContent()) break;

            List<Long> memberIds = members.getContent().stream()
                .map(Member::getId)
                .toList();

            // 회원별 기존 미션 ID 매핑
            Map<Long, List<Long>> existingMissionsMap = memberMissionRepository
                .findMissionIdsByMembersAndPeriodType(memberIds, periodType.name())
                .stream()
                .collect(Collectors.groupingBy(
                    row -> ((Number) row[0]).longValue(),   // memberId
                    Collectors.mapping(row -> ((Number) row[1]).longValue(), Collectors.toList())
                ));

            List<MemberMission> toSave = new ArrayList<>();

            for (Member member : members) {
                List<Long> already = existingMissionsMap.getOrDefault(member.getId(), Collections.emptyList());
                List<Mission> available = allMissions.stream()
                    .filter(m -> !already.contains(m.getId()))
                    .collect(Collectors.toList());

                Collections.shuffle(available);
                List<Mission> selected = available.stream().limit(countPerMember).toList();

                for (Mission mission : selected) {
                    toSave.add(MemberMission.builder()
                        .member(member)
                        .mission(mission)
                        .status(MissionStatus.NOT_STARTED)
                        .count(0)
                        .completedAt(null)
                        .build());
                }
            }

            memberMissionRepository.saveAll(toSave);

        } while (members.hasNext());
    }

/*    @Transactional
    public void assignDailyMissionsToAll() {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            assignNewMissions(member.getUuid(), PeriodType.DAILY, 2);
        }
    }

    @Transactional
    public void assignWeeklyMissionsToAll() {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            assignNewMissions(member.getUuid(), PeriodType.WEEKLY, 4);
        }
    }

    @Transactional
    public void assignNewMissions(String memberUuid, PeriodType periodType, int count) {
        Member member = memberRepository.findByUuid(memberUuid)
            .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        // 이미 부여된 미션 제외
        List<Long> alreadyAssignedIds = memberMissionRepository.findMissionIdsByMemberAndPeriodType(member.getId(), periodType.name());
        List<Mission> candidates = missionRepository.findRandomMissionsExcluding(periodType.name(), alreadyAssignedIds, count);

        List<MemberMission> newMissions = candidates.stream()
            .map(mission -> MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.NOT_STARTED)
                .count(0)
                .completedAt(null)
                .build())
            .toList();

        memberMissionRepository.saveAll(newMissions);
    }*/


}
