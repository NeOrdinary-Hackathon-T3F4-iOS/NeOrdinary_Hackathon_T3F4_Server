package com.t3f4.zerowaste.avatar.dto;

import com.t3f4.zerowaste.avatar.domain.AvatarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvatarCreateRequest {
    private String hiddenName;
    private String realName;
    private AvatarType avatarType;
}
