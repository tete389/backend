package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.repository.UserRepository;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class UserProfileService {

    //
    private final UserRepository userRepository;


    private  final UserProfileRepository userProfileRepository;

    public UserProfileService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

//    public Optional<UserProfile> findByUser(UserData userData){
//        return userProfileRepository.findByUserData(userData);
//    }

    public void createProfile(UserData userid, String name) throws BaseException {

        /// validate
        if (Objects.isNull(name)) {
            throw AuthException.signUpFailNameNull();
        }
        /// svae
        UserProfile entity = new UserProfile();
        entity.setUser(userid);
        entity.setName(name);
        userProfileRepository.save(entity);
    }

    //
    public UserProfile updateName(String id, String name) throws BaseException {
        Optional<UserProfile> opt = userProfileRepository.findById(id);
        if (opt.isEmpty()){
            throw UserException.userNotFound();
        }
        UserProfile userProfile = opt.get();
        userProfile.setName(name);
        return userProfileRepository.save(userProfile);
    }


    //
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    //

}
