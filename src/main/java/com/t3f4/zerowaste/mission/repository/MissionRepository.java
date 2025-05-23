package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.PeriodType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = "SELECT * FROM mission WHERE period_type = :periodType ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Mission> findRandomMissionsByPeriodType(@Param("periodType") String periodType, @Param("limit") int limit);

    @Query(value = """
        SELECT * FROM mission 
        WHERE period_type = :periodType 
        AND id NOT IN (:excludeIds)
        ORDER BY RAND()
        LIMIT :count
        """, nativeQuery = true)
    List<Mission> findRandomMissionsExcluding(
        @Param("periodType") String periodType,
        @Param("excludeIds") List<Long> excludeIds,
        @Param("count") int count
    );

    List<Mission> findByPeriodType(PeriodType periodType);
}