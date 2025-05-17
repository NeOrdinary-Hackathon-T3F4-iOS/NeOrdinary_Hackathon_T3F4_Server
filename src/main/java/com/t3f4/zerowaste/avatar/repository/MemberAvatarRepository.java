package com.t3f4.zerowaste.avatar.repository;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.MemberAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberAvatarRepository extends JpaRepository<MemberAvatar, Long> {
    @Query("select ma" +
            " from MemberAvatar ma join ma.member m join fetch ma.avatar a" +
            " where m.uuid=:uuid and ma.currentGroth<100")
    Optional<MemberAvatar> getAvatar(@Param("uuid") String uuid);
}