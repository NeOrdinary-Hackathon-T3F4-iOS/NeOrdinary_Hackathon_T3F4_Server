package com.t3f4.zerowaste.avatar.domain;

import com.t3f4.zerowaste.global.domain.BaseEntity;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.mission.domain.RewardType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class PointUse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @Enumerated(EnumType.STRING)
    private RewardType rewardType;
}