package com.t3f4.zerowaste.avatar.repository;

import com.t3f4.zerowaste.avatar.domain.MemberAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAvatarRepository extends JpaRepository<MemberAvatar, Long> {
}
