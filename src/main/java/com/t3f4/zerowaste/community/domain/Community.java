package com.t3f4.zerowaste.community.domain;

import com.t3f4.zerowaste.domain.BaseEntity;
import com.t3f4.zerowaste.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String title;
    private String content;
}
