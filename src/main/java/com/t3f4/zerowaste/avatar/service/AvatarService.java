package com.t3f4.zerowaste.avatar.service;

import com.t3f4.zerowaste.apipayload.code.status.ErrorStatus;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;
import com.t3f4.zerowaste.avatar.domain.Avatar;
import com.t3f4.zerowaste.avatar.domain.GrothLevel;
import com.t3f4.zerowaste.avatar.domain.MemberAvatar;
import com.t3f4.zerowaste.avatar.dto.AvatarCreateRequest;
//import com.t3f4.zerowaste.avatar.dto.AvatarHistoryResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.repository.AvatarRepository;
import com.t3f4.zerowaste.avatar.repository.GrothLevelRepository;
import com.t3f4.zerowaste.avatar.repository.MemberAvatarRepository;
import com.t3f4.zerowaste.member.domain.Member;
import com.t3f4.zerowaste.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarService {

    private final AvatarRepository avatarRepository;
    private final MemberRepository memberRepository;
    private final MemberAvatarRepository memberAvatarRepository;
    private final GrothLevelRepository grothLevelRepository;

    public AvatarResponse createAvatar(String uuid, AvatarCreateRequest request) {
        Member member = (Member) memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

        Avatar avatar = Avatar.builder()
                .hiddenName(request.getHiddenName())
                .realName(request.getRealName())
                .avatarType(request.getAvatarType())
                .build();

        avatarRepository.save(avatar);

        GrothLevel grothLevel = GrothLevel.builder()
                .level(1)
                .requirement(0)
                .label("새싹")
                .imageUrl("https://example.com/seedling.png")
                .avatar(avatar)
                .build();
        grothLevelRepository.save(grothLevel);

        MemberAvatar memberCharacter = MemberAvatar.builder()
                .member(member)
                .avatar(avatar)
                .currentGroth(0)
                .revealed(false)
                .build();

        memberAvatarRepository.save(memberCharacter);

        return AvatarResponse.from(avatar, grothLevel);
    }

//    public List<AvatarHistoryResponse> getAvatarHistory(String uuid) {
//    }
}
