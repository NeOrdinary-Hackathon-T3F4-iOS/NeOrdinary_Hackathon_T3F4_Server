package com.t3f4.zerowaste.mission.repository;

import com.t3f4.zerowaste.mission.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}