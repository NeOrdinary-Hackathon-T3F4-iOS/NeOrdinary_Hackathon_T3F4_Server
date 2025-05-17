package com.t3f4.zerowaste.mission.domain;

import com.t3f4.zerowaste.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private int count;
    private int reward;
    private Date deadline;
    @Enumerated(EnumType.STRING)
    private PeriodType periodType;
}
