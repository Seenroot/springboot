package com.seenroot.springboot.error;

public interface CommonError {
    int getErrorCode();

    String getErrorMsg();

    CommonError setErrMsg(String errMsg);
}
