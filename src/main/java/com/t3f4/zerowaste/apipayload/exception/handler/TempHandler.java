package com.t3f4.zerowaste.apipayload.exception.handler;

import com.t3f4.zerowaste.apipayload.code.BaseErrorCode;
import com.t3f4.zerowaste.apipayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}