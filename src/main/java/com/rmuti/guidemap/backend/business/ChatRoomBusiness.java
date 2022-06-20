package com.rmuti.guidemap.backend.business;


import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.exception.UserException;
//import com.rmuti.guidemap.backend.models.MChatRoomCreateRequest;
import com.rmuti.guidemap.backend.models.MChatRoomRequest;
import com.rmuti.guidemap.backend.models.MChatRoomResponse;
import com.rmuti.guidemap.backend.services.ChatRoomService;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChatRoomBusiness {

    private final ChatRoomService chatRoomService;

    private final TokenService tokenService;

    ///
    public MChatRoomResponse getAllChatRoom() throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MChatRoomResponse ChatRoomData = chatRoomService.findAllChatRoom();
        if(ChatRoomData == null){
            throw ChatException.failDataNull();
        }
        return ChatRoomData;
    }

    ///
    public ChatRoom getChatRoomByName(String chatRoomName) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        Optional<ChatRoom> ChatRoomData = chatRoomService.findByName(chatRoomName);
        if(ChatRoomData.isEmpty()){
            throw ChatException.ChatRoomFailDataNull();
        }
        return ChatRoomData.get();
    }

    ///
    public MChatRoomResponse getChatRoomsByLocationId(String locationId) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MChatRoomResponse ChatRoomData = chatRoomService.findByLocationId(locationId);
        if(ChatRoomData == null){
            throw ChatException.ChatRoomFailDataNull();
        }
        return ChatRoomData;
    }

    ///
    public String createChatRoom(MChatRoomRequest request) throws BaseException {
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        ChatRoom create = chatRoomService.createChatRoom(
                request.getCrName(),
                request.getLocationId()
        );
        Optional<ChatRoom> opt = chatRoomService.findByName(create.getCrName());
        if(opt.isEmpty()){
            throw ChatException.ChatRoomCreateFail();
        }
        return "create chat.room compiled";
    }

    ///
    public String updateChatRoom(MChatRoomRequest request) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        ChatRoom update = chatRoomService.updateChatRoom(
                request.getCrId(),
                request.getCrName(),
                request.getLocationId()
        );

        if (!request.getCrName().equals(update.getCrName())){
            throw ChatException.updateFail();
        }
        return  "update chat.room compiled";
    }

    ///
    public String delChatRoomByName(MChatRoomRequest name) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        String messageDeleted = chatRoomService.deleteChatRoomByName(name.getCrName());
        if(messageDeleted.isEmpty()){
            throw ChatException.deleteFail();
        }
        return messageDeleted;
    }

    ///
    public String delChatRoomById(String id) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        String messageDeleted = chatRoomService.deleteChatRoomById(id);
        if(messageDeleted.isEmpty()){
            throw ChatException.deleteFail();
        }
        return messageDeleted;
    }

    ///
    public String delChatRoomByLocation(MChatRoomRequest location) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();


        String messageDeleted = chatRoomService.deleteAllChatRoomByLocation(location.getLocationId());
        if(messageDeleted.isEmpty()){
            throw ChatException.deleteFail();
        }
        return messageDeleted;
    }

}
