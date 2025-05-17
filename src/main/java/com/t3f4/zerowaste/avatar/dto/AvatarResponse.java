package com.t3f4.zerowaste.avatar.dto;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.AvatarType;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import lombok.*;

@Getter
@AllArgsConstructor
public class AvatarResponse {
    private Long id;
    private String realName;
    private GrothLevelResponse grothLevel; // 추가

    public static AvatarResponse from(Avatar avatar, GrothLevel grothLevel) {
        return new AvatarResponse(
                avatar.getId(),
                avatar.getRealName(),
                GrothLevelResponse.from(grothLevel)
        );
    }
}
