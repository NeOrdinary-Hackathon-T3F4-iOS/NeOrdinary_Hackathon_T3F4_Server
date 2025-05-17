package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("""
    SELECT mm FROM MemberMission mm
    JOIN FETCH mm.mission m
    WHERE mm.member.uuid = :uuid
""")
    List<MemberMission> findByMemberUuidWithMission(@Param("uuid") String uuid);
}
