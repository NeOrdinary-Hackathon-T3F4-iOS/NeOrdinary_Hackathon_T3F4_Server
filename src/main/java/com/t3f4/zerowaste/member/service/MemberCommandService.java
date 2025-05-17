package com.t3f4.zerowaste.member.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.member.MemberConverter;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {

    private final MemberRepository memberRepository;

    public void insertMember(String memberUuid) {
        if (memberUuid == null || memberUuid.isBlank()) {
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        Member member = MemberConverter.mapToMember(memberUuid);
        memberRepository.save(member);
    }
}