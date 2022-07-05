package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.business.ChatMessageBusiness;
import com.rmuti.guidemap.backend.business.ChatRoomBusiness;
import com.rmuti.guidemap.backend.business.ImageDataBusiness;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.*;
import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.LocationData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatApi {

    //
    private final ChatRoomBusiness chatRoomBusiness;

    private final ChatMessageBusiness chatMessageBusiness;

    private final ImageDataBusiness imageDataBusiness;


    //
    @PostMapping("/createChatRoom")
    public ResponseEntity<String> createChatRoom(@RequestBody MChatRoomRequest request) throws BaseException {
        String messageCompiled = chatRoomBusiness.createChatRoom(request);
        return ResponseEntity.ok(messageCompiled);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @GetMapping("/getAllChatRoom")
    public ResponseEntity<MChatRoomResponse> getAllChatRoom() throws BaseException {
        MChatRoomResponse res = chatRoomBusiness.getAllChatRoom();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getChatRoomsByLocation")
    public ResponseEntity<MChatRoomResponse> getChatRoomInLocationId(@RequestBody List<MChatRoomRequest> crLocationId) throws BaseException {
        MChatRoomResponse res = chatRoomBusiness.getChatRoomsByLocationId(crLocationId);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/updateChatRoom")
    public ResponseEntity<String> updateChatRoom(@RequestBody MChatRoomRequest request) throws BaseException {
        String chatRoom = chatRoomBusiness.updateChatRoom(request);
        return ResponseEntity.ok(chatRoom);
    }

    @PostMapping("/uploadImageChatRoom")
    public ResponseEntity<String> uploadLocationImage(@RequestPart MultipartFile file, @RequestPart String chatRoomId) throws BaseException, IOException {
        String response = imageDataBusiness.uploadImageChatRoom(file , chatRoomId);
        return ResponseEntity.ok(response);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @PostMapping("/deleteChatRoomById")
    public ResponseEntity<String> deleteChatRoomById(@RequestBody MChatRoomRequest chatRoomId) throws BaseException {
        String messageCompiled = chatRoomBusiness.delChatRoomById(chatRoomId.getCrId());
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/deleteChatRoomByLocation")
    public ResponseEntity<String> deleteChatRoomByLocation(@RequestBody MChatRoomRequest crLocationId) throws BaseException {
        String messageCompiled = chatRoomBusiness.delChatRoomByLocation(crLocationId.getCrLocationId());
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/saveMessageToChatRoom")
    public ResponseEntity<String> createChatMessage(@RequestBody MChatMessageRequest request) throws BaseException {
        String messageCompiled = chatMessageBusiness.sendChatMessage(request);
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/getMessageByChatRoom")
    public ResponseEntity<MChatMessageResponse> getMessageByChatRoomId(@RequestBody MChatRoomRequest chatRoomId) throws BaseException {
        MChatMessageResponse res = chatMessageBusiness.getChatMessageByChatRoomId(chatRoomId.getCrId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMessageInChatRoom")
    public ResponseEntity<String> deleteChatMessage(@RequestBody MChatMessageDeleteRequest request) throws BaseException {
        String messageCompiled = chatMessageBusiness.deleteChatMessage(request);
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/sendImageMessageToChatRoom")
    public ResponseEntity<String> sendMessageImage(@RequestPart MultipartFile file, @RequestPart String chatRoomId) throws BaseException, IOException {
        String response = imageDataBusiness.sendMessageImage(file , chatRoomId);
        return ResponseEntity.ok(response);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
