package com.rmuti.guidemap.backend.exception;

public class AuthException extends BaseException{

    public AuthException(String code) {
        super("auth."+ code);
    }

    // check request null 
//    public static AuthException signUpRequestNull() {
//        return new AuthException("sign_up.request.null");
//    }
//
//    //
//    public static AuthException signUpEmailNull() {
//        return new AuthException("sign_up.email.null");
//    }

    //
    public static AuthException signInFailEmailNotFound() {

        return new AuthException("sign_in.fail");
    }

    public static AuthException signInFailPasswordIncorrect() {

        return new AuthException("sign_in.fail");
    }

    //
    public static AuthException signUpFailEmailNull() {

        return new AuthException("sign_up.email.null");
    }

    public static AuthException signUpFailProfileNull() {

        return new AuthException("sign_up.profile.null");
    }

    public static AuthException signUpFailPasswordNull() {

        return new AuthException("sign_up.fail.password.null");
    }

    public static AuthException signUpFailNameNull() {

        return new AuthException("sign_up.fail.name.null");
    }

    public static AuthException signUpFailEmailDuplicated() {

        return new AuthException("sign_up.fail.email.duplicate");
    }
}
