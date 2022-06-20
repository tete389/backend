package com.rmuti.guidemap.backend.business;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.mapper.UserMapper;
import com.rmuti.guidemap.backend.models.MAuthResponse;
import com.rmuti.guidemap.backend.models.MLoginRequest;
import com.rmuti.guidemap.backend.models.MRegisterRequest;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.services.UserProfileService;
import com.rmuti.guidemap.backend.services.UserDataService;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@Service
public class AuthBusiness {

    //
    private final UserDataService userService;

    private final TokenService tokenService;

    private final UserProfileService userProfileService;

    private final UserMapper userMapper;


    public MAuthResponse register(MRegisterRequest request) throws BaseException {
        UserProfile createProfile = userProfileService.createProfile(request.getName());
        UserData createUser = userService.createUser(createProfile.getUpId(), request.getEmail(), request.getPassword());

        Optional<UserData> byEmail = userService.findByEmail(createUser.getUdEmail());
        if(byEmail.isEmpty()){
            throw AuthException.signUpFailEmailNull();
        }
        if(!byEmail.get().getUdEmail().equals(request.getEmail())){
            throw AuthException.signUpFailEmailNull();
        }
        MAuthResponse response = new MAuthResponse();
        response.setToken(tokenService.tokenize(createProfile.getUpId()));
        return response;
    }


    public MAuthResponse login(MLoginRequest request) throws BaseException {
        // check email
        Optional<UserData> byEmail = userService.findByEmail(request.getEmail());
        if (byEmail.isEmpty()) {
            throw AuthException.signInFailEmailNotFound();
        }
        // check password form email
        UserData userData = byEmail.get();
        checkPassword(request.getPassword(),userData.getUdPassword());

        //
        Optional<UserProfile> userProfileId = userProfileService.findUserProfileById(userData.getUserProfileId());
        if (userProfileId.isEmpty()) {
            throw AuthException.signUpFailEmailNull();
        }
        MAuthResponse response = new MAuthResponse();
        response.setToken(tokenService.tokenize(userProfileId.get().getUpId()));
        return response;
    }

    public MAuthResponse loginGoogle(MLoginRequest request) throws BaseException {
        // check email
        Optional<UserData> byGoogleEmail = userService.findByGoogleEmail(request.getEmail());

        /// Google Email not found
        if (byGoogleEmail.isEmpty()) {
            // create google email
            UserProfile createProfile = userProfileService.createProfile(request.getEmail());
            UserData createUser = userService.createUserByGoogle(createProfile.getUpId(), request.getEmail(), request.getPassword());

            Optional<UserData> byEmailGoogle = userService.findByGoogleEmail(createUser.getUdEmail());
            if(byEmailGoogle.isEmpty()){
                throw AuthException.signUpFailEmailNull();
            }
            if(!byEmailGoogle.get().getUdEmail().equals(request.getEmail())){
                throw AuthException.signUpFailEmailNull();
            }
            MAuthResponse response = new MAuthResponse();
            response.setToken(tokenService.tokenize(createProfile.getUpId()));
            return response;
        }

        /// email found
        // check password form email
        UserData userData = byGoogleEmail.get();
        checkPassword(request.getPassword(), userData.getUdPassword());

        Optional<UserProfile> userProfileId = userProfileService.findUserProfileById(userData.getUserProfileId());
        if (userProfileId.isEmpty()) {
            throw AuthException.signUpFailEmailNull();
        }
        MAuthResponse response = new MAuthResponse();
        response.setToken(tokenService.tokenize(userProfileId.get().getUpId()));
        return response;
    }

    public void checkPassword(String getPassword, String UdPassword) throws BaseException{
        if (!userService.matchPassword(getPassword, UdPassword)) {
            throw AuthException.signInFailPasswordIncorrect();
        }
    }

}