package com.t3f4.zerowaste.avatar.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hiddenName;
    private String realName;
    @Enumerated(EnumType.STRING)
    private AvatarType avatarType;
}
