package com.rmuti.guidemap.backend.services;

import java.util.Objects;
import java.util.Optional;

import com.rmuti.guidemap.backend.exception.AuthException;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.UserException;

import com.rmuti.guidemap.backend.repository.UserRepository;
import com.rmuti.guidemap.backend.table.UserData;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.Data;

@Data
@Service
public class UserService {

    //
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //
    public Optional<UserData> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    //
    public Optional<UserData> findById(String id){
        return userRepository.findById(id);
    }

    //
    public  boolean matchPassword(String rawPassword, String encodedPassword){
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    //
    public UserData createUser(String email, String password) throws BaseException {
        /// validate
        if (Objects.isNull(email)) {
            throw AuthException.signUpFailEmailNull();
        }
        if (Objects.isNull(password)) {
            throw AuthException.signUpFailPasswordNull();
        }
        /// verify
       if ( userRepository.existsByEmail(email)) {
           throw AuthException.signUpFailEmailDuplicated();
       }

        /// save
        UserData entity = new UserData();
        entity.setEmail(email);
        entity.setPassword(passwordEncoder.encode(password));
        //entity.setName(name);
        return userRepository.save(entity);
    }

    //
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

}
