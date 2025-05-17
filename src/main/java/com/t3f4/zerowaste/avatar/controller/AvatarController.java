package com.t3f4.zerowaste.avatar.controller;

import com.t3f4.zerowaste.apipayload.ApiResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarCreateRequest;
//import com.t3f4.zerowaste.avatar.dto.AvatarHistoryResponse;
import com.t3f4.zerowaste.avatar.dto.AvatarResponse;
import com.t3f4.zerowaste.avatar.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @PostMapping
    public ResponseEntity<ApiResponse<AvatarResponse>> createAvatar(
            @RequestParam String uuid,
            @RequestBody AvatarCreateRequest request
    ) {
        AvatarResponse response = avatarService.createAvatar(uuid, request);
        return ResponseEntity.ok(ApiResponse.onSuccess(response));
    }

//    @GetMapping("/history")
//    public ResponseEntity<ApiResponse<List<AvatarHistoryResponse>>> getAvatarHistory(
//            @RequestParam String uuid
//    ) {
//        List<AvatarHistoryResponse> history = avatarService.getAvatarHistory(uuid);
//        return ResponseEntity.ok(ApiResponse.onSuccess(history));
//    }
//
//    @GetMapping("/{avatarId}")
//    public ResponseEntity<String> getAvatarImage(
//            @RequestParam String uuid,
//            @PathVariable Long avatarId
//    ) {
//        String imageUrl = avatarService.getAvatarImage(uuid, avatarId);
//        return ResponseEntity.ok(imageUrl);
//    }

}
