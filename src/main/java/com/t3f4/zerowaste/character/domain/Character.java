package com.t3f4.zerowaste.character.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String hiddenName;
    private String realName;
    @ManyToOne(fetch = FetchType.LAZY)
    private CharacterType characterType;
}
