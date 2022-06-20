package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.business.ChatMessageBusiness;
import com.rmuti.guidemap.backend.business.ChatRoomBusiness;
import com.rmuti.guidemap.backend.business.ImageDataBusiness;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.*;
import com.rmuti.guidemap.backend.table.ChatRoom;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


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
    public ResponseEntity<MChatRoomResponse> getChatRoomInLocationId(@RequestBody MChatRoomRequest request) throws BaseException {
        MChatRoomResponse res = chatRoomBusiness.getChatRoomsByLocationId(request.getLocationId());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/getChatRoomsByName")
    public ResponseEntity<ChatRoom> getChatRoomByName(@RequestBody MChatRoomRequest request) throws BaseException {
        ChatRoom res = chatRoomBusiness.getChatRoomByName(request.getCrName());
        return ResponseEntity.ok(res);
    }


    @PostMapping("/updateChatRoom")
    public ResponseEntity<String> updateChatRoom(@RequestBody MChatRoomRequest request) throws BaseException {
        String chatRoom = chatRoomBusiness.updateChatRoom(request);
        return ResponseEntity.ok(chatRoom);
    }


    @PostMapping("/deleteChatRoom")
    public ResponseEntity<String> deleteChatRoom(@RequestBody MChatRoomRequest request) throws BaseException {
        String messageCompiled = chatRoomBusiness.delChatRoomByName(request);
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<String> createChatMessage(@RequestBody MChatMessageRequest request) throws BaseException {
        String messageCompiled = chatMessageBusiness.createChatMessage(request);
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/getMessage")
    public ResponseEntity<MChatMessageResponse> getMessageByChatRoomId(@RequestBody MChatRoomRequest request) throws BaseException {
        MChatMessageResponse res = chatMessageBusiness.getChatMessageByChatRoomId(request);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/deleteMessage")
    public ResponseEntity<String> deleteChatMessage(@RequestBody MChatMessageDeleteRequest request) throws BaseException {
        String messageCompiled = chatMessageBusiness.deleteChatMessageById(request);
        return ResponseEntity.ok(messageCompiled);
    }

    @PostMapping("/uploadChatMessageImage")
    public ResponseEntity<String> uploadChatMessageImage(@RequestPart MultipartFile file, @RequestPart MChatMessageRequest request) throws BaseException, IOException {
        String response = imageDataBusiness.uploadImageChatMessage(file , request);
        return ResponseEntity.ok(response);
        // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }
}
