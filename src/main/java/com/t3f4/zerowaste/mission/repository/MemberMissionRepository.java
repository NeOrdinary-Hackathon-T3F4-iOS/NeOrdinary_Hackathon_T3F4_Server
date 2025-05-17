package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.mission.domain.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findAllByMemberId(Long memberId);
}
