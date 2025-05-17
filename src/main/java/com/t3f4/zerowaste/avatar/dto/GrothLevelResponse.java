package com.t3f4.zerowaste.avatar.dto;

import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.AvatarType;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GrothLevelResponse {
    private int level;
    private int requirement;
    private String label;

    public static GrothLevelResponse from(GrothLevel grothLevel) {
        return new GrothLevelResponse(
                grothLevel.getLevel(),
                grothLevel.getRequirement(),
                grothLevel.getLabel()
        );
    }
}
