package com.t3f4.zerowaste.member.dto;

import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontScreenDto {
    long sun;
    long pot;
    long time;
    long fertilizer;
    private int currentGroth;
    AvatarResponse avatarData;
    long weekly_current;
    long weekly_total;
    long daily_current;
    long daily_total;
}