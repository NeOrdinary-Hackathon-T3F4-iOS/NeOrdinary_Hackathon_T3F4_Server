package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionCountDto {
    private PeriodType periodType;
    private long missionCount;
    private long finishedCount;
}