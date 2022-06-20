package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.repository.UserDataRepository;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserProfileService {

    //
    private final UserDataRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    //
    public List<UserProfile> findAllUserProfile(){
        return userProfileRepository.findAll();
    }

    //
    public Optional<UserProfile> findUserProfileById(String id){
        return userProfileRepository.findById(id);
    }

    public UserProfile createProfile(String UpName) throws BaseException {

        /// validate
        if (Objects.isNull(UpName)) {
            throw AuthException.signUpFailNameNull();
        }
        /// save
        UserProfile entity = new UserProfile();
        entity.setUpStatus("No Data");
        entity.setUpImage("No Data");
        entity.setUpName(UpName);
       return userProfileRepository.save(entity);
    }

    //
    public String updateUserProfile(String id, String name, String status, String image) throws BaseException {
        Optional<UserProfile> opt = userProfileRepository.findById(id);
        if (opt.isEmpty()){
            throw UserException.userNotFound();
        }
        UserProfile profile = opt.get();

        /// validate
        if (name.isEmpty()) name = profile.getUpName();
        if (status.isEmpty()) status = profile.getUpStatus();
        if (image.isEmpty()) image = profile.getUpImage();

        /// update
        profile.setUpName(name);
        profile.setUpStatus(status);
        profile.setUpImage(image);
        userProfileRepository.save(profile);
        return "update user.profile complied";
    }

    //
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    //

}
