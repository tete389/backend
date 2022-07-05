package com.rmuti.guidemap.backend.business;


import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.exception.LocationException;
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

    private final ChatMessageBusiness chatMessageBusiness;

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
    public MChatRoomResponse getChatRoomsByLocationId(List<MChatRoomRequest> locationId) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MChatRoomResponse ChatRoomData = chatRoomService.findAllByLocationId(locationId);
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
                request.getCrLocationId()
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
                request.getCrLocationId()
        );

        if (!request.getCrId().equals(update.getCrId())){
            throw ChatException.updateFail();
        }
        return  "update chat.room compiled";
    }

    ///
    public String delChatRoomById(String chatRoomId) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();
        Optional<ChatRoom> findById = chatRoomService.findById(chatRoomId);
        if(findById.isEmpty()){
            throw ChatException.deleteFail();
        }
        ChatRoom chatRoom = findById.get();

        chatMessageBusiness.deleteChatMessageByChatRoom(chatRoom.getCrId());

        String messageDeleted = chatRoomService.deleteChatRoomById(chatRoom.getCrId());
        if(messageDeleted.isEmpty()){
            throw ChatException.deleteFail();
        }
        return messageDeleted;
    }

    ///
    public String delChatRoomByLocation(String locationId) throws BaseException{
        if (!tokenService.checkAdmin()){
            throw UserException.accessDenied();
        }
        UserProfile resToken = tokenService.checkTokenUser();

        List<ChatRoom> findByLocationId = chatRoomService.findAllChatRoomByLocation(locationId);
        if (findByLocationId.isEmpty()){
            throw LocationException.locationFailDataNull();
        }

        for (ChatRoom chatRoom : findByLocationId) {
            chatMessageBusiness.deleteChatMessageByChatRoom(chatRoom.getCrId());
        }

        String messageDeleted = chatRoomService.deleteAllChatRoomByLocation(locationId);
        if(messageDeleted.isEmpty()){
            throw ChatException.deleteFail();
        }
        return messageDeleted;
    }



}
