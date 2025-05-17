package com.t3f4.zerowaste.avatar.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.avatar.repository.PointUseRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointUseService {

    private final MemberRepository memberRepository;
    private final PointUseRepository pointUseRepository;

    public int getUseCount(String uuid, RewardType rewardType) {
        Member member = (Member) memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        return pointUseRepository.countByMemberAndRewardType(member, rewardType);
    }
}
