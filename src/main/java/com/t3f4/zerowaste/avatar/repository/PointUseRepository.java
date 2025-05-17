package com.t3f4.zerowaste.avatar.repository;

import com.t3f4.zerowaste.avatar.domain.PointUse;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.domain.RewardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUseRepository extends JpaRepository<PointUse, Long> {
    int countByMemberAndRewardType(Member member, RewardType rewardType);
}
