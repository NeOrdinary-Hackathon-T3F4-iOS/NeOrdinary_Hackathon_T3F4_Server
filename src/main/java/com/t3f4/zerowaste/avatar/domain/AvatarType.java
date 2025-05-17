package com.t3f4.zerowaste.avatar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AvatarType {
    SEED("씨앗"),
    FLOWER("꽃");

    private final String label;
}
