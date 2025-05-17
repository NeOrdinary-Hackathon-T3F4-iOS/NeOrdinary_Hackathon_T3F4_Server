package com.t3f4.zerowaste.member.repository;

import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUuid(String userUuid);
    @Query(value = "SELECT * FROM mission WHERE period_type = :periodType ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Mission> findRandomMissionsByPeriodType(@Param("periodType") String periodType, @Param("limit") int limit);

}