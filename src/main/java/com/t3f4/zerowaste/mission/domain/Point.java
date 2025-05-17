package com.t3f4.zerowaste.mission.domain;

import com.t3f4.zerowaste.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int reward;
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;
}
