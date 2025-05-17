package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.Mission;
import com.t3f4.zerowaste.mission.domain.MissionStatus;
import com.t3f4.zerowaste.mission.domain.PeriodType;
import com.t3f4.zerowaste.mission.domain.RewardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionResponse {
    private long id;
    private String title;
    private RewardType reward;
    private MissionStatus status;
    private PeriodType periodType;

    public static MissionResponse from(Mission mission) {
        return MissionResponse.builder()
                .id(mission.getId())
                .title(mission.getTitle())
                .reward(mission.getReward())
                .status(mission.getStatus())
                .periodType(mission.getPeriodType())
                .build();
    }
}
