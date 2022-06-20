package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.exception.FileException;
import com.rmuti.guidemap.backend.exception.LocationException;
import com.rmuti.guidemap.backend.repository.ChatMessageRepository;
import com.rmuti.guidemap.backend.repository.ImageDataRepository;
import com.rmuti.guidemap.backend.repository.LocationDataRepository;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.table.ChatMessage;
import com.rmuti.guidemap.backend.table.ImageData;
import com.rmuti.guidemap.backend.table.LocationData;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;

    private final UserProfileRepository userProfileRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final LocationDataRepository locationDataRepository;


    //
    public Optional<ImageData> findById(String id) {
        return imageDataRepository.findById(id);
    }


    //
    public String createImageUserProfile(String path, String imgName, UserProfile userProfile) throws BaseException {
        /// validate
        if (Objects.isNull(path)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(imgName)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(userProfile)) {
            throw FileException.fileNull();
        }

        //update
        ImageData imageData = new ImageData();
        imageData.setImgPath(path);
        imageData.setImgName(imgName);
        imageDataRepository.save(imageData);

        userProfile.setUpImage(imgName);
        userProfileRepository.save(userProfile);

        /// check after update
        Optional<UserProfile> optUp = userProfileRepository.findById(userProfile.getUpId());
        if(optUp.isEmpty()){
            throw ChatException.sendMessageFail();
        }
        Optional<ImageData> optImg = imageDataRepository.findById(imageData.getImgId());
        if(optImg.isEmpty()){
            throw ChatException.sendMessageFail();
        }
     return "update userProfile Image complied";
    }

    public String createImageLocation(String path, String imgName, LocationData locationData) throws BaseException {
        /// validate
        if (Objects.isNull(path)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(imgName)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(locationData)) {
            throw FileException.fileNull();
        }

        //update
        ImageData imageData = new ImageData();
        imageData.setImgPath(path);
        imageData.setImgName(imgName);
        imageDataRepository.save(imageData);

        locationData.setLdImage(imgName);
        locationDataRepository.save(locationData);

        /// check after update
        Optional<LocationData> optLd = locationDataRepository.findById(locationData.getLdId());
        if(optLd.isEmpty()){
            throw LocationException.locationUpdateFail();
        }
        Optional<ImageData> optImg = imageDataRepository.findById(imageData.getImgId());
        if(optImg.isEmpty()){
            throw LocationException.locationUpdateFail();
        }
        return "update Location Image complied";
    }


    public String createImageChatMessage(String sender, String receiver, String imgName ,String path) throws BaseException {
        /// validate
        if (Objects.isNull(sender)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(receiver)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(imgName)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(path)) {
            throw FileException.fileNull();
        }

        /// save
        ImageData imageData = new ImageData();
        imageData.setImgPath(path);
        imageData.setImgName(imgName);
        imageDataRepository.save(imageData);

        ChatMessage entity = new ChatMessage();
        entity.setUserProfileId(sender);
        entity.setChatRoomId(receiver);
        entity.setCmMessage("non");
        entity.setCmStatus(true);
        entity.setCmTypeMessage("image");
        entity.setCmImage(imgName);
        chatMessageRepository.save(entity);

        /// check after save
        Optional<ChatMessage> optCm = chatMessageRepository.findById(entity.getCmId());
        if(optCm.isEmpty()){
            throw ChatException.sendMessageFail();
        }
        Optional<ImageData> optImg = imageDataRepository.findById(imageData.getImgId());
        if(optImg.isEmpty()){
            throw ChatException.sendMessageFail();
        }

        return "send Image complied";
    }


}
