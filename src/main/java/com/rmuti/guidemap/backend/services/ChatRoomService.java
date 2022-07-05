package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.MChatRoomRequest;
import com.rmuti.guidemap.backend.models.MChatRoomResponse;
import com.rmuti.guidemap.backend.repository.ChatRoomRepository;
import com.rmuti.guidemap.backend.repository.LocationDataRepository;
import com.rmuti.guidemap.backend.repository.UserProfileRepository;
import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.LocationData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final LocationDataRepository locationDataRepository;



    //
    public MChatRoomResponse findAllByLocationId(List<MChatRoomRequest> locationId) {
        MChatRoomResponse response = new MChatRoomResponse();
        for (MChatRoomRequest request : locationId) {
            response.getResult().addAll(chatRoomRepository.findByCrLocationId(request.getCrLocationId()));
        }
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
    public List<ChatRoom> findAllChatRoomByLocation(String locationId){
        return chatRoomRepository.findByCrLocationId(locationId);
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
        Optional<LocationData> opt =  locationDataRepository.findById(locationId);
        if (opt.isEmpty()){
            throw ChatException.ChatRoomCreateFail();
        }
        /// verify
        if (chatRoomRepository.existsByCrName(chatRoomName)) {
            throw ChatException.failDuplicated();
        }
        /// save
        ChatRoom entity = new ChatRoom();
        entity.setCrImage("no data");
        entity.setCrName(chatRoomName);
        entity.setCrLocationId(opt.get().getLdId());
        return chatRoomRepository.save(entity);
    }

    //
    public ChatRoom updateChatRoom(String id, String chatRoomName, String locationId) throws BaseException {
        /// validate
        Optional<ChatRoom> opt = chatRoomRepository.findById(id);
        if (opt.isEmpty()){
            throw ChatException.failDataNull();
        }
        ChatRoom entity = opt.get();


        if (Objects.isNull(locationId)) {
            throw ChatException.failLocationNull();
        }

        Optional<LocationData> optLd =  locationDataRepository.findById(locationId);
        if (optLd.isEmpty()){
            throw ChatException.updateFail();
        }

        /// verify
        if (chatRoomRepository.existsByCrName(chatRoomName)) {
            throw ChatException.failDuplicated();
        }
        if (Objects.isNull(chatRoomName)) {
            chatRoomName = entity.getCrName();
        }
        if (chatRoomName.isEmpty()) {
            chatRoomName = entity.getCrName();
        }

        /// save
        entity.setCrLocationId(optLd.get().getLdId());
        entity.setCrName(chatRoomName);
        return chatRoomRepository.save(entity);
    }


    //
    public String deleteChatRoomById(String chatRoomId) throws BaseException {
        /// validate
        if (Objects.isNull(chatRoomId)) {
            throw ChatException.ChatRoomFailDataNull();
        }
        /// delete
        chatRoomRepository.deleteById(chatRoomId);
        return "delete ChatRoom ById Success";
    }

    //
    public String deleteAllChatRoomByLocation(String locationId) throws BaseException {
        /// validate
        if (Objects.isNull(locationId)) {
            throw ChatException.ChatRoomFailDataNull();
        }

        List<ChatRoom> opt = chatRoomRepository.findByCrLocationId(locationId);
        if (opt.isEmpty()){
            throw ChatException.ChatRoomFailDataNull();
        }

        /// delete
        chatRoomRepository.deleteAll(opt);
        return "deleteChatRoomByLocation";
    }


}
