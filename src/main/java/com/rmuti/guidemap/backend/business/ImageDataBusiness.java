package com.rmuti.guidemap.backend.business;

//import com.rmuti.guidemap.backend.config.UrlConfig;
import com.rmuti.guidemap.backend.exception.*;
import com.rmuti.guidemap.backend.models.MChatMessageRequest;
import com.rmuti.guidemap.backend.services.*;
import com.rmuti.guidemap.backend.table.LocationData;
import com.rmuti.guidemap.backend.table.UserProfile;
import com.rmuti.guidemap.backend.util.FilesUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@Service
public class ImageDataBusiness {

    private final ImageDataService imageDataService;

    private final LocationDataService locationDataService;

    private final TokenService tokenService;

    public static String uploadDirectory = System.getProperty("user.dir");

    public String uploadImageUserProfile(MultipartFile file) throws BaseException {
        UserProfile resToken = tokenService.checkTokenUser();


        String pathSet = FilesUtil.pathSet;
        String pathUserUrl = FilesUtil.imageUserProfileUrl;
        String upId = resToken.getUpId();
        String dir = pathSet + pathUserUrl;

        String imgName = manageImage(upId, dir, file);

        return imageDataService.createImageUserProfile(pathUserUrl, imgName, resToken);
    }


    public String uploadImageChatMessage(MultipartFile file, MChatMessageRequest request) throws BaseException {
        UserProfile resToken = tokenService.checkTokenUser();

        String pathSet = FilesUtil.pathSet;
        String pathChatMessageUrl = FilesUtil.imageChatMessageUrl;
        String upId = resToken.getUpId();
        String dir = pathSet + pathChatMessageUrl;

        String imgName = manageImage(upId, dir, file);

        return imageDataService.createImageChatMessage(
                resToken.getUpId(),
                request.getReceiver(),
                imgName,
                pathChatMessageUrl
                );
    }


    public String uploadImageLocation(MultipartFile file, LocationData request) throws BaseException {
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();

        Optional<LocationData> optLd = locationDataService.findById(request.getLdId());
        if (optLd.isEmpty()){
            throw LocationException.locationUpdateFail();
        }
        LocationData ldRes = optLd.get();

        String pathSet = FilesUtil.pathSet;
        String pathLocationUrl = FilesUtil.imageLocationDataUrl;
        String ldId = ldRes.getLdId();
        String dir = pathSet + pathLocationUrl;

        String imgName = manageImage(ldId, dir, file);

        return imageDataService.createImageLocation(pathLocationUrl, imgName, ldRes);
    }

    ///
    public String manageImage(String id, String dir, MultipartFile file) throws BaseException{

        if(file == null){
            throw FileException.fileNull();
        }

        if(file.getSize() > 1048576 * 5 ){
            throw FileException.fileMaxSize();
        }

        String contentType = file.getContentType();
        if (contentType == null){
            throw FileException.fileUnSupport();
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String tempName = UUID.randomUUID().toString().replaceAll("-", "");
        //String imgName = userId +"timeStamp:"+ timeStamp +"random:"+ tempname + ".png";
        String imgName = "ID-"+id+"-T"+timeStamp+"-R-"+tempName+ ".png";
        StringBuilder fileNames;
        fileNames = new StringBuilder();

        Path fileNameAndPath = Paths.get(uploadDirectory + dir, imgName);
        fileNames.append(file.getOriginalFilename()).append(" ");
        try {
            byte[] bytes = file.getBytes();
            Files.write(fileNameAndPath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgName;
    }

}
