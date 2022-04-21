package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.controller.AuthController;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.authModels.MAuthResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignInResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignUpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}
