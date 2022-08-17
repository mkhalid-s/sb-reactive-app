package com.khalid.sb3reactiveapp.exception;

public class InputNumberValidationException extends RuntimeException{

    private static final String ERR_MSG = "Allowed range less than 1000";

    private static final int errCode = 100;

    private final String responseMsg;


    public InputNumberValidationException(String responseMsg) {
        super(ERR_MSG);
        this.responseMsg = responseMsg;
    }

/*    public InputNumberValidationException(int responseMsg) {
        super(ERR_MSG);
        this.responseMsg = String.valueOf(responseMsg);
    }*/

    public static int getErrCode() {
        return errCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }
}
