package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.repository.UserRepository;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserProfileService {

    //
    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    //
    public UserProfile createProfile(UserData user, String name) throws BaseException {

        /// validate
        if (Objects.isNull(name)) {
            throw AuthException.signUpFailNameNull();
        }
        /// svae
        UserProfile entity = new UserProfile();
        entity.setUserData(user);
        entity.setName(name);
       return userProfileRepository.save(entity);
    }

    //
    public UserProfile updateName(String id, String name) throws BaseException {
        Optional<UserProfile> opt = userProfileRepository.findById(id);
        if (opt.isEmpty()){
            throw UserException.userNotFound();
        }
        UserProfile Profile = opt.get();
        Profile.setName(name);
        return userProfileRepository.save(Profile);
    }
    //
//    public UserProfile getUserProfile(UserData user) throws BaseException {
//        Optional<UserProfile> opt = userProfileRepository.findByUserData(user);
//        if (opt.isEmpty()){
//            throw UserException.userNotFound();
//        }
//        return opt.get();
//    }
//    //
//    public Optional<UserProfile> findByUser(UserData userData){
//        return userProfileRepository.findByUserData(userData);
//    }

    ////
    public String getUserProfileId(String userId) throws BaseException {
        Optional<String> opt = userProfileRepository.findIdProfile(userId);
        if (opt.isEmpty()){
            throw UserException.userNotFound();
        }
        return opt.get();
    }

    //
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    //

}
