package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.controller.UserController;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.services.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserService uService;
    private final UserController userController;


    public UserApi(UserService uService, UserController userController) {
        this.uService = uService;
        this.userController = userController;
    }


//    @GetMapping("/{id}")
//    public ResponseEntity<String> getUserById(@PathVariable("id") String id) throws BaseException{
//        String userById = uService.getUserById(id);
//        return ResponseEntity.ok(userById);
//    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadProfilePicture(@RequestPart MultipartFile file) throws BaseException {
        String uploadProfilePicture = userController.uploadProfilePicture(file);
        return ResponseEntity.ok(uploadProfilePicture);

    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws BaseException {
        String token = userController.refreshToken();
        return ResponseEntity.ok(token);
    }

}
