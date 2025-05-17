package com.t3f4.zerowaste.avatar.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.avatar.domain.*;
import com.t3f4.zerowaste.avatar.dto.AvatarCreateRequest;
//import com.t3f4.zerowaste.avatar.dto.AvatarHistoryResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.dto.GrothLevelResponse;
import com.t3f4.zerowaste.avatar.repository.AvatarRepository;
import com.t3f4.zerowaste.avatar.repository.GrothLevelRepository;
import com.t3f4.zerowaste.avatar.repository.MemberAvatarRepository;
import com.t3f4.zerowaste.avatar.repository.PointUseRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import com.t3f4.zerowaste.mission.domain.RewardType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final MemberRepository memberRepository;
    private final GrothLevelRepository grothLevelRepository;

    private final Map<RewardType, Integer> rewardPointMap = Map.of(
            RewardType.SUN, 1,
            RewardType.POT, 3,
            RewardType.TIME, 5,
            RewardType.FERTILIZER, 8
    );

    @Transactional
    public GrothLevelResponse updateGrowthLevel(String memberUuid, Long avatarId, RewardType rewardType) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        Avatar avatar = avatarRepository.findById(avatarId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._AVATAR_NOT_FOUND));

        // 기존 성장 레벨 조회 or 새로 생성
        GrothLevel grothLevel = grothLevelRepository.findByAvatar(avatar)
                .orElseGet(() -> GrothLevel.builder()
                        .avatar(avatar)
                        .level(1)
                        .requirement(0)
                        .label(GrothType.SPROUT)
                        .build());

        // rewardType에 따른 포인트 획득
        int rewardPoint = rewardPointMap.getOrDefault(rewardType, 0);

        // 기존 requirement + 새로운 포인트 누적 (최대 100으로 제한)
        int updatedRequirement = Math.min(grothLevel.getRequirement() + rewardPoint, 100);

        // label 결정
        GrothType newLabel = determineGrothType(updatedRequirement);

        // label이 GROWN으로 변경됐고 avatarType이 아직 설정 안된 경우 랜덤 설정
        if (newLabel == GrothType.GROWN && avatar.getAvatarType() == null) {
            avatar.setAvatarType(getRandomGrownAvatarType());
            avatarRepository.save(avatar);
        }

        // grothLevel 정보 업데이트
        grothLevel.setRequirement(updatedRequirement);
        grothLevel.setLabel(newLabel);
        grothLevel.setLevel(determineLevelFromPoints(updatedRequirement));

        grothLevel = grothLevelRepository.save(grothLevel);

        return GrothLevelResponse.from(grothLevel);
    }

    private GrothType determineGrothType(int points) {
        if (points >= 100) return GrothType.GROWN;
        else if (points >= 50) return GrothType.LEAVES;
        else return GrothType.SPROUT;
    }

    private int determineLevelFromPoints(int points) {
        if (points >= 100) return 3;
        else if (points >= 50) return 2;
        else return 1;
    }

    private AvatarType getRandomGrownAvatarType() {
        AvatarType[] grownTypes = {AvatarType.SHAMPO, AvatarType.BODY, AvatarType.WASH};
        return grownTypes[new Random().nextInt(grownTypes.length)];
    }

}
