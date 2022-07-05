package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.exception.FileException;
import com.rmuti.guidemap.backend.exception.LocationException;
import com.rmuti.guidemap.backend.repository.*;
import com.rmuti.guidemap.backend.table.*;
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

    private final ChatRoomRepository chatRoomRepository;

    private final LocationDataRepository locationDataRepository;


    ///
    public Optional<ImageData> findById(String id) {
        return imageDataRepository.findById(id);
    }


    ///
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

    ///
    public String createImageChatRoom(String path, String imgName, ChatRoom chatRoom) throws BaseException {
        /// validate
        if (Objects.isNull(path)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(imgName)) {
            throw FileException.fileNull();
        }
        if (Objects.isNull(chatRoom)) {
            throw FileException.fileNull();
        }

        //update
        ImageData imageData = new ImageData();
        imageData.setImgPath(path);
        imageData.setImgName(imgName);
        imageDataRepository.save(imageData);

        chatRoom.setCrImage(imgName);
        chatRoomRepository.save(chatRoom);

        /// check after update
        Optional<ChatRoom> optCr = chatRoomRepository.findById(chatRoom.getCrId());
        if(optCr.isEmpty()){
            throw ChatException.updateFail();
        }
        Optional<ImageData> optImg = imageDataRepository.findById(imageData.getImgId());
        if(optImg.isEmpty()){
            throw ChatException.updateFail();
        }
        return "update ChatRoom Image complied";
    }

    ///
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


    ///
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
        entity.setCmUserProfileId(sender);
        entity.setCmChatRoomId(receiver);
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
