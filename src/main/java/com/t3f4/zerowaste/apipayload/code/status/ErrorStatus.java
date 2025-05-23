package com.t3f4.zerowaste.apipayload.code.status;

import com.t3f4.zerowaste.apipayload.code.BaseErrorCode;
import com.t3f4.zerowaste.apipayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "해당 회원이 존재하지 않습니다."),
    _MISSION_MEMBER_NOT_MATCH(HttpStatus.FORBIDDEN, "MISSION403", "권한이 없습니다."),
    _MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "미션이 없습니다."),
    _LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "GROTH_404", "성장 정보가 존재하지 않습니다."),
    _REWARD_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "REWARD_400", "사용 가능한 아이템이 부족합니다."),
    _AVATAR_NOT_FOUND(HttpStatus.NOT_FOUND, "AVATAR_404", "아바타를 찾을 수 없습니다."),
    _MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_404", "해당 멤버 미션을 찾을 수 없습니다."),
    _INVALID_MISSION_STATUS(HttpStatus.BAD_REQUEST, "MISSION_400", "보상을 받을 수 있는 상태가 아닙니다."),
    _UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "MISSION_403", "해당 미션에 접근할 권한이 없습니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .httpStatus(httpStatus)
                .isSuccess(false).build();
    }
}