package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.business.ImageDataBusiness;
import com.rmuti.guidemap.backend.business.UserBusiness;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.MUserProfileRequest;
import com.rmuti.guidemap.backend.repository.UserDataRepository;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserApi {

    private final UserBusiness userBusiness;

    private final ImageDataBusiness imageDataBusiness;


    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String token = userBusiness.refreshToken();
        return ResponseEntity.ok(token);
    }

    @PostMapping("/updateUserProfileCurrent")
    public ResponseEntity<String> updateUserProfile(@RequestBody MUserProfileRequest request) throws BaseException {
        String response = userBusiness.updateUserProfile(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserProfileCurrent")
    public ResponseEntity<UserProfile> getUserProfileOnly() throws BaseException {
        UserProfile response = userBusiness.getUserProfileCurrent();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUserProfile")
    public ResponseEntity<List<UserProfile>> getAllUserProfile() throws BaseException {
        List<UserProfile> response = userBusiness.getAllUserProfile();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserDataCurrent")
    public ResponseEntity<UserDataRepository.UserDataResponse> getUserDataCurrent() throws BaseException {
        UserDataRepository.UserDataResponse response = userBusiness.getUserDataCurrent();
        return ResponseEntity.ok(response);
    }

    //
    @PostMapping("/uploadUserProfileImage")
    public ResponseEntity<String> uploadImageUserProfile(@RequestPart MultipartFile file) throws BaseException, IOException {
        String response = imageDataBusiness.uploadImageUserProfile(file);
        return ResponseEntity.ok(response);
    }


}
