package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
//    List<Mission> findByMember(Member member);
    @Query("SELECT m FROM Mission m JOIN MemberMission mm ON mm.mission = m WHERE mm.member = :member")
    List<Mission> findByMember(@Param("member") Member member);
}
