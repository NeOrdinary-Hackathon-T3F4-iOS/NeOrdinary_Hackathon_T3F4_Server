package com.t3f4.zerowaste.mission.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.global.service.S3Service;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.Photo;
import com.t3f4.zerowaste.mission.repository.MemberMissionRepository;
import com.t3f4.zerowaste.mission.repository.MissionRepository;
import com.t3f4.zerowaste.mission.repository.PhotoRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
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
        Long missionId,
        String memberUuid,
        MultipartFile file
    ) {
        if (missionId == null || memberUuid == null) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        Member findMember = memberRepository.findByUuid(memberUuid)
            .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        Mission findMission = missionRepository.findById(missionId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(findMember.getId(), missionId)
            .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));

        MissionStatus missionStatus = memberMission.getStatus();

        if (missionStatus.equals(MissionStatus.GET_REWARDS) || missionStatus.equals(MissionStatus.COMPLETED)) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        String imageUrl = s3Service.upload(file);

        Photo photo = Photo.builder()
            .memberMission(memberMission)
            .imageUrl(imageUrl)
            .build();

        photoRepository.save(photo);

        memberMission.incrementCount();

        // 미션 성공 처리
        if (memberMission.getCount() >= findMission.getCount()) {
            memberMission.updateMissionStatus(MissionStatus.GET_REWARDS);
            memberMission.updateMissionCompletedAt(LocalDateTime.now());
        }
    }
}
