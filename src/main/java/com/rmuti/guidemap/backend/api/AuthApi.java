package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.business.AuthBusiness;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.MAuthResponse;
import com.rmuti.guidemap.backend.models.MLoginRequest;
import com.rmuti.guidemap.backend.models.MRegisterRequest;
import com.rmuti.guidemap.backend.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthApi {

    //
    private final AuthBusiness authBusiness;

    public AuthApi(AuthBusiness authBusiness) {
        this.authBusiness = authBusiness;
    }

    //
    @PostMapping("/register")
    public ResponseEntity<MAuthResponse> register(@RequestBody MRegisterRequest request) throws BaseException {
        MAuthResponse registerResponse = authBusiness.register(request);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<MAuthResponse> login(@RequestBody MLoginRequest request) throws BaseException {
        MAuthResponse loginResponse = authBusiness.login(request);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/loginGoogle")
    public ResponseEntity<MAuthResponse> loginGoogle(@RequestBody MLoginRequest request) throws BaseException {
        MAuthResponse loginResponse = authBusiness.loginGoogle(request);
        return ResponseEntity.ok(loginResponse);
    }

//    @PostMapping("/test")
//    public ResponseEntity<Object> profileResponse() throws BaseException {
//        String userId = SecurityUtil.getCurrentUserId().get();
//        System.out.print("token id : "+userId);
//        //UserProfile testRess = authController.testRes1(request);
//        return ResponseEntity.ok(userId);
//    }


}
