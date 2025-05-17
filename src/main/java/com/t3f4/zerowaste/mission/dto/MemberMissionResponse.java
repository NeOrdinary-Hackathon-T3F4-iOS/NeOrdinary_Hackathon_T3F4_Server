package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberMissionResponse {
    private Long id;
    private String title;
    private RewardType reward;
    private MissionStatus status;
    private PeriodType periodType;

    public static MemberMissionResponse from(MemberMission memberMission) {
        Mission mission = memberMission.getMission();

        return MemberMissionResponse.builder()
                .id(memberMission.getId())
                .title(mission.getTitle())
                .reward(mission.getReward())
                .periodType(mission.getPeriodType())
                .status(memberMission.getStatus())
                .build();
    }
}
