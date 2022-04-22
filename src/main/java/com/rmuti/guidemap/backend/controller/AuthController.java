package com.rmuti.guidemap.backend.controller;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.mapper.UserMapper;
import com.rmuti.guidemap.backend.models.authModels.MAuthResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignInResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignUpResponse;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.services.UserProfileService;
import com.rmuti.guidemap.backend.services.UserService;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class AuthController {

    //
    private final UserService userService;

    private final TokenService tokenService;

    private  final UserProfileService userProfileService;

    private final UserMapper userMapper;

    public AuthController(UserService userService, TokenService tokenService, UserProfileService userProfileService, UserMapper userMapper) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.userProfileService = userProfileService;
        this.userMapper = userMapper;
    }

    public String signUpService(MSignUpResponse request) throws BaseException {

        UserData createUser = userService.createUser(request.getEmail(), request.getPassword());
        //MAuthResponse rAuthResponse = userMapper.toRAuthResponse(createUser);

//        if(request.passAdmin == app.token.passwordStaff )
//        {
//            admin role
//        }

        Optional<UserData> byEmail = userService.findByEmail(createUser.getEmail());
        if(byEmail.isEmpty()){
            throw AuthException.signUpFailEmailNull();
        }
        UserData userData = byEmail.get();
        UserProfile createProfile = userProfileService.createProfile(userData, request.getName());

        return tokenService.tokenize(createProfile);
    }


    public String signInService(MSignInResponse request) throws BaseException {
        // check email
        Optional<UserData> byEmail = userService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw AuthException.signInFailEmailNotFound();
        }
        // check password form email
        UserData userData = byEmail.get();
        if (!userService.matchPassword(request.getPassword(), userData.getPassword())) {
            throw AuthException.signInFailPasswordIncorrect();
        }
        //
        UserProfile userProfile = userProfileService.getUserProfile(userData);

        return tokenService.tokenize(userProfile);


    }

    public UserProfile testRes1(UserData request) throws BaseException{
       // UserProfile byUser =
        //UserProfile userProfile = byUser.
        return userProfileService.getUserProfile(request);
    }

    public UserProfile testRes2(UserData request) throws BaseException{

        Optional<UserProfile> byUser = userProfileService.findByUser(request);
        if (byUser.isEmpty()){
            throw UserException.userNotFound();
        }
        return byUser.get();
    }
}