package com.t3f4.zerowaste.avatar.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.avatar.domain.PointUse;
import com.t3f4.zerowaste.avatar.repository.PointUseRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.RewardType;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void useReward(String uuid, RewardType rewardType) {
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        // 사용 가능한 아이템이 있는지 확인
        PointUse pointUse = (PointUse) pointUseRepository
                .findFirstByMemberAndRewardType(member, rewardType)
                .orElseThrow(() -> new GeneralException(ErrorStatus._REWARD_NOT_ENOUGH));

        // 하나 삭제 (아이템 사용 처리)
        pointUseRepository.delete(pointUse);
    }
}
