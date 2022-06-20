package com.rmuti.guidemap.backend.business;

import com.rmuti.guidemap.backend.exception.*;
import com.rmuti.guidemap.backend.models.MUserDataResponse;
import com.rmuti.guidemap.backend.models.MUserProfileResponse;
import com.rmuti.guidemap.backend.repository.UserDataRepository;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.services.UserProfileService;
import com.rmuti.guidemap.backend.services.UserDataService;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserBusiness {

    private final UserDataService uService;
    private final TokenService tokenService;
    private final UserProfileService userProfileService;

    public String refreshToken() throws BaseException {
        UserProfile resToken = tokenService.checkTokenUser();
        //UserProfile user = userProfileService.getUserProfile(userData);
        String userProfileId = resToken.getUpId();
        return tokenService.tokenize(userProfileId);
    }


    public String updateUserProfile(UserProfile request) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        return userProfileService.updateUserProfile(
                resToken.getUpId(),
                request.getUpName(),
                request.getUpStatus(),
                request.getUpImage()
        );
    }

    public MUserProfileResponse getAllUserProfile() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();
        return userProfileService.findAllUserProfile();
    }

    public UserProfile getUserProfileCurrent() throws BaseException{
        return tokenService.checkTokenUser();
    }

    public MUserDataResponse getUserDataCurrent() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MUserDataResponse byIdUserProfile = uService.findUserById(resToken.getUpId());
        if (byIdUserProfile == null) {
            throw AuthException.signInFailEmailNotFound();
        }
        return byIdUserProfile;
    }

}
