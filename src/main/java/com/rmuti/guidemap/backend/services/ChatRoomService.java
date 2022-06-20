package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.MChatRoomResponse;
import com.rmuti.guidemap.backend.repository.ChatRoomRepository;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.table.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserProfileRepository userProfileRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    //
    public MChatRoomResponse findByLocationId(String locationId) {
        MChatRoomResponse response = new MChatRoomResponse();
        response.getResult().addAll(chatRoomRepository.findByLocationId(locationId));
        return response;
    }

    //
    public Optional<ChatRoom> findByName(String name) {
        return chatRoomRepository.findByCrName(name);
    }

    public Optional<ChatRoom> findById(String id) {
        return chatRoomRepository.findById(id);
    }

    //
    public MChatRoomResponse findAllChatRoom(){
        MChatRoomResponse response = new MChatRoomResponse();
        response.getResult().addAll(chatRoomRepository.findAll());
        return response;
    }

    //
    public ChatRoom createChatRoom(String chatRoomName, String locationId) throws BaseException {
        /// validate
        if (Objects.isNull(chatRoomName)) {
            throw ChatException.failNameNull();
        }
        if (Objects.isNull(locationId)) {
            throw ChatException.failLocationNull();
        }

        /// verify
        if (chatRoomRepository.existsByCrName(chatRoomName)) {
            throw ChatException.failDuplicated();
        }
        /// save
        ChatRoom entity = new ChatRoom();
        entity.setCrName(chatRoomName);
        entity.setLocationId(locationId);
        return chatRoomRepository.save(entity);
    }

    //
    public ChatRoom updateChatRoom(String id, String chatRoomName, String locationId) throws BaseException {
        /// validate
        if (Objects.isNull(chatRoomName)) {
            throw ChatException.failNameNull();
        }
        if (Objects.isNull(locationId)) {
            throw ChatException.failLocationNull();
        }
        Optional<ChatRoom> opt = chatRoomRepository.findById(id);
        if (opt.isEmpty()){
            throw ChatException.failDataNull();
        }

        /// verify
        if (chatRoomRepository.existsByCrName(chatRoomName)) {
            throw ChatException.failDuplicated();
        }

        /// save
        ChatRoom entity = opt.get();
        entity.setLocationId(locationId);
        entity.setCrName(chatRoomName);
        return chatRoomRepository.save(entity);
    }


    //
    public String deleteChatRoomById(String id) throws BaseException {
        /// validate
        if (Objects.isNull(id)) {
            throw ChatException.ChatRoomFailDataNull();
        }
        /// delete
        chatRoomRepository.deleteById(id);
        return "deleteChatRoomById";
    }

    //
    public String deleteChatRoomByName(String name) throws BaseException {
        /// validate
        if (Objects.isNull(name)) {
            throw ChatException.ChatRoomFailDataNull();
        }

        Optional<ChatRoom> opt = chatRoomRepository.findByCrName(name);
        if (opt.isEmpty()){
            throw ChatException.ChatRoomFailDataNull();
        }

        /// delete
        chatRoomRepository.deleteById(opt.get().getCrId());
        return "deleteChatRoomByName";
    }

    //
    public String deleteAllChatRoomByLocation(String locationId) throws BaseException {
        /// validate
        if (Objects.isNull(locationId)) {
            throw ChatException.ChatRoomFailDataNull();
        }

        List<ChatRoom> opt = chatRoomRepository.findByLocationId(locationId);
        if (opt.isEmpty()){
            throw ChatException.ChatRoomFailDataNull();
        }

        /// delete
        chatRoomRepository.deleteAll(opt);
        return "deleteChatRoomByLocation";
    }


}
