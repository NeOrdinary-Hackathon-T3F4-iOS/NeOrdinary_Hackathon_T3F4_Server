package com.t3f4.zerowaste.avatar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AvatarType {
    SHAMPO("샴푸바"),
    BODY("바디바"),
    WASH("설거지바");

    private final String label;
}
