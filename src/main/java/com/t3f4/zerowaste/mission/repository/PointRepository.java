package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.mission.domain.Point;
import com.t3f4.zerowaste.mission.dto.PointDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select new com.t3f4.zerowaste.mission.dto.PointDto(p.rewardType, sum(p.reward))" +
            " from Point p join p.member m" +
            " where m.uuid=:uuid" +
            " group by p.rewardType")
    List<PointDto> findPointByUuid(@Param("uuid") String uuid);

    @Query("select new com.t3f4.zerowaste.mission.dto.PointDto(p.rewardType, count(p))" +
            " from PointUse p join p.member m" +
            " where m.uuid=:uuid" +
            " group by p.rewardType")
    List<PointDto> findPointUseByUuid(@Param("uuid") String uuid);
}