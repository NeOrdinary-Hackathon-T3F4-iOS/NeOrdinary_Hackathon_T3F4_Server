package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionSimpleCount {
    private PeriodType periodType;
    private long missionCount;
}