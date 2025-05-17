package com.t3f4.zerowaste.character.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GrothLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int level;
    private String condition;
    private String label;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private Character character;
}
