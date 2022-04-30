package com.rmuti.guidemap.backend.controller;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.mapper.UserMapper;
import com.rmuti.guidemap.backend.models.authModels.MSignInResponse;
import com.rmuti.guidemap.backend.models.authModels.MSignUpResponse;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.services.UserProfileService;
import com.rmuti.guidemap.backend.services.UserService;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class AuthController {

    //
    private final UserService userService;

    private final TokenService tokenService;

    private final UserProfileService userProfileService;

    private final UserMapper userMapper;


    public String signUpService(MSignUpResponse request) throws BaseException {

        UserData createUser = userService.createUser(request.getEmail(), request.getPassword());
        //MAuthResponse rAuthResponse = userMapper.toRAuthResponse(createUser);

//        if(request.passAdmin == app.token.passwordStaff )
//        {
//            admin role
//        }
//        Optional<UserData> byEmail = userService.findByEmail(createUser.getEmail());
//        if(byEmail.isEmpty()){
//            throw AuthException.signUpFailEmailNull();
//        }
//        UserData userData = byEmail.get();
        UserProfile createProfile = userProfileService.createProfile(createUser, request.getName());
        //String userProfileId = createProfile.getId();
        return tokenService.tokenize(createProfile.getId());
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
       // UserProfile userProfile = userProfileService.getUserProfile(userData);
        String userProfileId = userProfileService.getUserProfileId(userData.getId());
        return tokenService.tokenize(userProfileId);

    }

//    public UserProfile testRes1(UserData request) throws BaseException{
//        return userProfileService.getUserProfile(request);
//    }
//
//    public UserProfile testRes2(UserData request) throws BaseException{
//
//        Optional<UserProfile> byUser = userProfileService.findByUser(request);
//        if (byUser.isEmpty()){
//            throw UserException.userNotFound();
//        }
//        return byUser.get();
//    }
}