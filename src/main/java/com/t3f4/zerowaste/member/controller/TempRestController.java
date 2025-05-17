package com.t3f4.zerowaste.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
//    기본 화면: GET /, Auth / 사용자 이름, 진행상황{단계, 종류}, 현재 포인트, 현재 키우고 있는 캐릭터{캐릭터 종류, 캐릭터 이름, 상태, 이미지 링크}, 수행할 수 있는 미션 목록[{user_mission id, 미션 제목, 미션 이름, 포인트, 진행상황(여러 개 만족해야 할 때), 목표, 마감일}]
//    수행할 미션 목록 확인: GET /missions/recommanded, Auth / 수행할 수 있는 미션 목록[{user_mission id, 미션 제목, 미션 이름, 포인트, 진행상황(여러 개 만족해야 할 때), 목표, 마감일}]
//    수행한 미션 목록 확인: GET /missions/finished, Auth / 수행한 미션 목록[{user_mission id, 미션 제목, 미션 이름, 포인트, 목표, 수행일}]
//    미션 인증: POST /missions/progress, Auth, user_mission id, Multipartfile / 미션 성공 여부, 현재 미션 상태{user_mission id, 미션 제목, 미션 이름, 포인트, 진행상황(여러 개 만족해야 할 때), 목표, 마감일}, 현재 포인트
//    미션 개별 기록 보기: GET /missions/{id}, Auth, user_mission id / 사진 제출 기록[{사진 업로드 id, 사진 업로드 날짜}]
//    미션 인증 기록 보기: GET /missions/record/{id}, Auth, 사진 업로드 id / Multipartfile
//    모은 캐릭터 정보: GET /collections, Auth / 모은 캐릭터 목록[{캐릭터 종류, 캐릭터 이름, 이미지 링크, 설명, 모은 캐릭터 수}]
//    캐릭터 이미지 전송: GET /character/{id}, 캐릭터 id / Multipartfile
//    캐릭터 상태 이미지 전송: GET /character/{id}/status/{id}, 캐릭터 id, 상태 id / Multipartfile
//    캐릭터 키운 목록 보기: GET /history, Auth / 캐릭터 키운 기록[{캐릭터 종류, 캐릭터 이름, 이미지 링크, 완성일}]
//    커뮤니티 글 목록 보기: GET /community, (없음) / 게시글 목록[{게시글 id, 게시글 제목, 작성자 닉네임, 생성일}]
//    커뮤니티 글 보기: GET /community/{id}, 게시글 id / 게시글 제목, 게시글 내용, 작성자 닉네임, 게시글 생성일
//    커뮤니티 글 검색: POST /community/search, 제목 조건, 제목+내용 조건, 닉네임 조건, 생성일 이전 조건, 생성일 이후 조건 / 게시글 목록[{게시글 id, 게시글 제목, 작성자 닉네임, 생성일}]
//    커뮤니티 글 등록: POST /community, Auth, 게시글 제목, 게시글 내용 / 생성된 게시글 id(리다이렉트는 알아서)
}