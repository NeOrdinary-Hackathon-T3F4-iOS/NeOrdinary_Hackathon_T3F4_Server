package com.t3f4.zerowaste.avatar.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class GrothLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int level;
    private int requirement;
    @Enumerated(EnumType.STRING)
    private GrothType label;
    @ManyToOne(fetch = FetchType.LAZY)
    private Avatar avatar;
}
