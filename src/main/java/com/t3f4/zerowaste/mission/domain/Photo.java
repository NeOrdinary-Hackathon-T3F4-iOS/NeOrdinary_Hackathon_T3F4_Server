package com.t3f4.zerowaste.mission.domain;

import com.t3f4.zerowaste.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Photo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private MemberMission memberMission;
}