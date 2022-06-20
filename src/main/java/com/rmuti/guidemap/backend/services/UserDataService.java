package com.rmuti.guidemap.backend.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;

import com.rmuti.guidemap.backend.repository.UserDataRepository;
import com.rmuti.guidemap.backend.table.UserData;


import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDataService {

    private final UserDataRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public Optional<UserData> findByEmail(String email){
        return userRepository.findByUdEmail(email);
    }

    public Optional<UserData> findByGoogleEmail(String email){
        return userRepository.findByUdGoogleEmail(email);
    }

    public Optional<UserData> findById(String id){
        return userRepository.findById(id);
    }

    public List<UserData> findAllUserData(){
        return userRepository.findAll();
    }

    public Optional<UserDataRepository.UserDataResponse> findUserById(String id){
        return userRepository.findByUserProfileId(id);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public UserData createUser(String userProfile, String email, String password) throws BaseException {
        /// validate
        if (Objects.isNull(email)) {
            throw AuthException.signUpFailEmailNull();
        }
        if (Objects.isNull(password)) {
            throw AuthException.signUpFailPasswordNull();
        }
        if (Objects.isNull(userProfile)) {
            throw AuthException.signUpFailProfileNull();
        }
        /// verify
       if ( userRepository.existsByUdEmail(email)) {
           throw AuthException.signUpFailEmailDuplicated();
       }

        /// save
        UserData entity = new UserData();
        entity.setUserProfileId(userProfile);
        entity.setUdEmail(email);
        entity.setUdGoogleEmail("non : "+email);
        entity.setUdPassword(passwordEncoder.encode(password));
        entity.setUpRole("user");
        return userRepository.save(entity);
    }

    public UserData createUserByGoogle(String userProfile, String googleEmail, String password) throws BaseException {
        /// validate
        if (Objects.isNull(googleEmail)) {
            throw AuthException.signUpFailEmailNull();
        }
        if (Objects.isNull(password)) {
            throw AuthException.signUpFailPasswordNull();
        }
        if (Objects.isNull(userProfile)) {
            throw AuthException.signUpFailProfileNull();
        }

        /// verify
        if (userRepository.existsByUdGoogleEmail(googleEmail)) {
            throw AuthException.signUpFailEmailDuplicated();
        }

        /// save
        UserData entity = new UserData();
        entity.setUserProfileId(userProfile);
        entity.setUdEmail("non : "+googleEmail);
        entity.setUdGoogleEmail(googleEmail);
        entity.setUdPassword(passwordEncoder.encode(password));
        entity.setUpRole("user");
        return userRepository.save(entity);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

}
