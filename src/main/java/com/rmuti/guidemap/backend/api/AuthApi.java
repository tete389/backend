package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.controller.AuthController;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.MUserProfileResponse;
import com.rmuti.guidemap.backend.models.authModels.MAuthResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignInResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignUpResponse;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import com.rmuti.guidemap.backend.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthApi {

    //
    private final AuthController authController;

    public AuthApi(AuthController authController) {
        this.authController = authController;
    }

    //
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody MSignUpResponse request) throws BaseException {
        String signUpService = authController.signUpService(request);
        return ResponseEntity.ok(signUpService);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();

    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody MSignInResponse request) throws BaseException {
        String signInService = authController.signInService(request);
        return ResponseEntity.ok(signInService);
    }

    @PostMapping("/test")
    public ResponseEntity<Object> profileResponse() throws BaseException {
        String userId = SecurityUtil.getCurrentUserId().get();
        System.out.printf("token id : "+userId);
        //UserProfile testRess = authController.testRes1(request);
        return ResponseEntity.ok(userId);
    }
}
