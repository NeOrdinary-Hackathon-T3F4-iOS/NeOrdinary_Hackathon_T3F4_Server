package com.t3f4.zerowaste.member.domain;

import com.t3f4.zerowaste.global.domain.BaseEntity;
import com.t3f4.zerowaste.mission.domain.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, unique = true, nullable = false)
    private String uuid;

    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @Builder
    public Member(String uuid) {
        this.uuid = uuid;
    }
}