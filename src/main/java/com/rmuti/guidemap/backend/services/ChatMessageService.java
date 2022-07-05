package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.MChatMessageResponse;
import com.rmuti.guidemap.backend.repository.ChatMessageRepository;
import com.rmuti.guidemap.backend.table.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository catMessageRepository) {
        this.chatMessageRepository = catMessageRepository;
    }

    //
    public Optional<ChatMessage> findById(String id) {
        return chatMessageRepository.findById(id);
    }

    //
    public List<ChatMessage> findAllByChatRoomOrderCreateASC(String nameRoom){
        return chatMessageRepository.findByCmChatRoomIdOrderByCmCreatedAsc(nameRoom);
    }

    //
    public List<ChatMessage> findMessageByChatRoom(String id){
        return chatMessageRepository.findByCmChatRoomIdOrderByCmCreatedAsc(id);
    }

    //
    public MChatMessageResponse findMessageByChatRoomId(String id){
        MChatMessageResponse res = new MChatMessageResponse();
        res.getResult().addAll(chatMessageRepository.findByChatRoomIdOrderByCreatedASC(id));
        return res;
    }

    //
    public ChatMessage createChatMessage(String sender, String receiver, String message) throws BaseException {
        /// validate
        if (Objects.isNull(sender)) {
            throw ChatException.sendMessageFail();
        }
        if (Objects.isNull(receiver)) {
            throw ChatException.sendMessageFail();
        }

        /// save
        ChatMessage entity = new ChatMessage();
        entity.setCmUserProfileId(sender);
        entity.setCmChatRoomId(receiver);
        entity.setCmMessage(message);
        entity.setCmStatus(true);
        entity.setCmTypeMessage("message");
        entity.setCmImage("non");
        return chatMessageRepository.save(entity);
    }

    //
    public ChatMessage deleteChatMessageById(String id, String receiver) throws BaseException {
        /// validate
        if (Objects.isNull(id)) {
            throw ChatException.deleteFail();
        }
//        if (Objects.isNull(create)) {
//            throw ChatException.deleteFail();
//        }
        if (Objects.isNull(receiver)) {
            throw ChatException.deleteFail();
        }
         /// verify
        Optional<ChatMessage> opt = chatMessageRepository.findById(id);
        if(opt.isEmpty()){
            throw ChatException.deleteFail();
        }
        ChatMessage entity = opt.get();

//        if (!entity.getCmCreated().toString().equals(create)){
//            throw ChatException.deleteFail();
//        }
        if (!entity.getCmChatRoomId().equals(receiver)){
            throw ChatException.deleteFail();
        }

        /// update message
        entity.setCmStatus(false);

        return chatMessageRepository.save(entity);
    }


    //
    public String deleteAllChatMessageByChatRoom (String chatRoomId) throws BaseException {
        /// validate
        if (Objects.isNull(chatRoomId)) {
            throw ChatException.deleteFail();
        }

        List<ChatMessage> opt = chatMessageRepository.findByCmChatRoomIdOrderByCmCreatedAsc(chatRoomId);
        if (opt.isEmpty()){
            throw ChatException.deleteFail();
        }

        /// delete
        chatMessageRepository.deleteAll(opt);
        return "delete ChatMessage By ChatRoom";
    }
}
