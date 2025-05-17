package com.t3f4.zerowaste.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionStatDto {
    private String title;
    private int count_reward;
    private int count_current;
    private String content;
    private String reward;
    private String periodType;
    private List<ImageDto> images;
}