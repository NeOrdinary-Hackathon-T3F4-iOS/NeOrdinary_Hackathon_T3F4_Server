package com.t3f4.zerowaste.mission.dto;

import com.t3f4.zerowaste.mission.domain.PeriodType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
public class MissionStatRepoDto {
    private String title;
    private String content;
    private int count;
    private int reward;
    private Date deadline;
    private PeriodType periodType;
    private String memberId;
    private String imageUrl; //이건 다(M)
    //image의 시간 어디감

    public MissionStatRepoDto(String title, String content, int count, int reward, Date deadline,
                              PeriodType periodType, String memberId, String imageUrl) {
        this.title = title;
        this.content = content;
        this.count = count;
        this.reward = reward;
        this.deadline = deadline;
        this.periodType = periodType;
        this.memberId = memberId;
        this.imageUrl = imageUrl;
    }
}