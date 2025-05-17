package com.t3f4.zerowaste.mission.domain;

import com.t3f4.zerowaste.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;
    @Enumerated(EnumType.STRING)
    private MissionStatus status;
    private int count;
    private LocalDateTime completedAt;

    @Setter
    @OneToMany(mappedBy = "memberMission")
    private List<Photo> photos;

    public void incrementCount() {
        this.count++;
    }

    public void updateMissionStatus(MissionStatus status) {
        this.status = status;
    }

    public void updateMissionCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}