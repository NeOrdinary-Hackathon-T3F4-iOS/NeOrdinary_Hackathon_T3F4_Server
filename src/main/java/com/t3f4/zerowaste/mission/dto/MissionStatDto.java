package com.t3f4.zerowaste.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MissionStatDto {
    private String title;
    private String content;
    private int count;
    private int reward;
    private Date deadline;
    private String periodType;
    private List<String> imageUrl;
}