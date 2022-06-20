package com.rmuti.guidemap.backend.exception;

public class UserException extends BaseException{

    public UserException(String code) {
        super("user."+ code);
       
    }

    public static UserException userNotFound() {
        return new UserException("user.notFound");
    }

    public static UserException unAuthorized() {
        return new UserException("unauthorized");
    }

    public static UserException accessDenied() {
        return new UserException("access.denied");
    }



}
