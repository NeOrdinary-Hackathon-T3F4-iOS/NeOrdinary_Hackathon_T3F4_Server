package com.t3f4.zerowaste.avatar.dto;

import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import com.t3f4.zerowaste.avatar.domain.GrothType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GrothLevelResponse {
    private int level;
    private int requirement;
    private GrothType label;

    public static GrothLevelResponse from(GrothLevel grothLevel) {
        return new GrothLevelResponse(
                grothLevel.getLevel(),
                grothLevel.getRequirement(),
                grothLevel.getLabel()
        );
    }
}
