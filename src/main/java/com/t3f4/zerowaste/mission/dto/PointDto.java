package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class PointDto {
    private RewardType rewardType;
    private long amount;

    public PointDto(RewardType rewardType, long amount) {
        this.rewardType = rewardType;
        this.amount = amount;
    }
}