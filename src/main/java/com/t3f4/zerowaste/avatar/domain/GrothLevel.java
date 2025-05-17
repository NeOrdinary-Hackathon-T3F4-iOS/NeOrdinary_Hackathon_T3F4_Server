package com.t3f4.zerowaste.avatar.domain;

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
    private int requirement;
    private String label;
    @ManyToOne(fetch = FetchType.LAZY)
    private Avatar avatar;
}
