package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
public class MissionStatRepoDto {
    private String title;
    private int count_reward;
    private int count_current;
    private String content;
    private RewardType reward;
    private PeriodType periodType;
    private String uuid;
    private String imageUrl; //이건 다(M)
    private LocalDateTime createdAt;

    public MissionStatRepoDto(String title, int count_reward, int count_current, String content,
                              RewardType reward, PeriodType periodType, String uuid,
                              String imageUrl, LocalDateTime createdAt) {
        this.title = title;
        this.count_reward = count_reward;
        this.count_current = count_current;
        this.content = content;
        this.reward = reward;
        this.periodType = periodType;
        this.uuid = uuid;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}