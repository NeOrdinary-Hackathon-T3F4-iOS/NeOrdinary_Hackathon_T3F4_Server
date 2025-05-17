package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.mission.domain.MemberMission;
import com.t3f4.zerowaste.mission.dto.MissionStatRepoDto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @EntityGraph(attributePaths = {"member", "mission", "photo"})
    Optional<MemberMission> findById(long id);

    @Query("select new com.t3f4.zerowaste.mission.dto.MissionStatRepoDto(m.title, m.content," +
            "m.count, m.reward, m.deadline, m.periodType, me.name, p.imageUrl)" +
            " from MemberMission mm join mm.member me join mm.mission m left join mm.photos p" +
            " where mm.id=:id")
    List<MissionStatRepoDto> findByIdInDto(@Param("id") long id);
  
    @Query("""
    SELECT mm FROM MemberMission mm
    JOIN FETCH mm.mission m
    WHERE mm.member.uuid = :uuid
""")
    List<MemberMission> findByMemberUuidWithMission(@Param("uuid") String uuid);
}