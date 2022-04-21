package com.rmuti.guidemap.backend.controller;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.FileException;
import com.rmuti.guidemap.backend.exception.UserException;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.services.UserService;
import com.rmuti.guidemap.backend.table.UserData;
import com.rmuti.guidemap.backend.util.SecurityUtil;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserController {

    private final UserService uService;
    private final TokenService tokenService;


    public UserController(UserService uService, TokenService tokenService) {
        this.uService = uService;
        this.tokenService = tokenService;
    }

    public String refreshToken() throws BaseException {
        Optional<String> currentUserId = SecurityUtil.getCurrentUserId();
        if (currentUserId.isEmpty()){
            throw UserException.unAuthorized();
        }

        String userId = currentUserId.get();
        Optional<UserData> opt = uService.findById(userId);
        if (opt.isEmpty()){
            throw UserException.userNotFound();
        }

        UserData userData = opt.get();
        return tokenService.tokenize(userData);
    }


    public String uploadProfilePicture(MultipartFile file) throws BaseException {
        if (file == null) {
            throw FileException.fileNull();
        }
        if (file.getSize() > 1048576 * 2) {
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            throw FileException.fileUnSupport();
        }

        List<String> supportTypes = Arrays.asList("image/jpeg, image/png, image/apng");
        if (!supportTypes.contains(contentType)) {
            throw FileException.fileUnSupport();
        }

        // TODO : upload
        try {
            byte[] bytes = file.getBytes();
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return "";

    }



}
