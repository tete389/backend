package com.rmuti.guidemap.backend.exception;

public class UserException extends BaseException{

    public UserException(String code) {
        super("user."+ code);
       
    }

    public static UserException userNotFound() {
        return new UserException("not.found");
    }

    public static UserException unAuthorized() {
        return new UserException("unauthorized");
    }


}
