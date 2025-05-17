package com.t3f4.zerowaste.avatar.repository;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrothLevelRepository extends JpaRepository<GrothLevel, Long> {
    Optional<Object> findTopByAvatarOrderByLevelDesc(Avatar avatar);

    Optional<GrothLevel> findByAvatar(Avatar avatar);
}
