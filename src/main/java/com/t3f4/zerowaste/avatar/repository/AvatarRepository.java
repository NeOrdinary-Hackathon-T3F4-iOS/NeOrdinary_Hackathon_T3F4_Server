package com.t3f4.zerowaste.avatar.repository;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

}
