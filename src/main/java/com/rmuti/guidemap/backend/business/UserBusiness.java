package com.rmuti.guidemap.backend.business;

import com.rmuti.guidemap.backend.exception.*;
import com.rmuti.guidemap.backend.models.MUserProfileRequest;
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


    public String updateUserProfile(MUserProfileRequest request) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        return userProfileService.updateUserProfile(
                resToken.getUpId(),
                request.getName(),
                request.getStatus(),
                request.getImage()
        );
    }

    public List<UserProfile> getAllUserProfile() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();
        return userProfileService.findAllUserProfile();
    }

    public UserProfile getUserProfileCurrent() throws BaseException{
        return tokenService.checkTokenUser();
    }

    public UserDataRepository.UserDataResponse getUserDataCurrent() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        Optional<UserDataRepository.UserDataResponse> byIdUserProfile = uService.findUserById(resToken.getUpId());
        if (byIdUserProfile.isEmpty()) {
            throw AuthException.signInFailEmailNotFound();
        }
        return byIdUserProfile.get();
    }

}
