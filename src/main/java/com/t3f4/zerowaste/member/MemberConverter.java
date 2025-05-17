package com.t3f4.zerowaste.member;

import com.t3f4.zerowaste.member.domain.Member;

public class MemberConverter {

    public static Member mapToMember(String uuid) {
        return Member
            .builder()
            .uuid(uuid)
            .build();
    }
}
