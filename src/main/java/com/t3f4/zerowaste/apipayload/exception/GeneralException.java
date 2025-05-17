package com.t3f4.zerowaste.apipayload.exception;

import com.t3f4.zerowaste.apipayload.code.BaseErrorCode;
import com.t3f4.zerowaste.apipayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;
    ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}