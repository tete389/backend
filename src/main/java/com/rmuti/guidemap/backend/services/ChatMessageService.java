package com.rmuti.guidemap.backend.services;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.MChatMessageResponse;
import com.rmuti.guidemap.backend.repository.ChatMessageRepository;
import com.rmuti.guidemap.backend.table.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChatMessageService {

    private final ChatMessageRepository catMessageRepository;

    public ChatMessageService(ChatMessageRepository catMessageRepository) {
        this.catMessageRepository = catMessageRepository;
    }

    //
    public Optional<ChatMessage> findById(String id) {
        return catMessageRepository.findById(id);
    }

    //
    public List<ChatMessage> findAllByChatRoomOrderCreateASC(String nameRoom){
        return catMessageRepository.findByChatRoomIdOrderByCmCreatedAsc(nameRoom);
    }

    //
    public MChatMessageResponse findMessageByChatRoomId(String id){
        MChatMessageResponse res = new MChatMessageResponse();
        res.getResult().addAll(catMessageRepository.findByChatRoomIdOrderByCreatedASC(id));
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
        entity.setUserProfileId(sender);
        entity.setChatRoomId(receiver);
        entity.setCmMessage(message);
        entity.setCmStatus(true);
        entity.setCmTypeMessage("message");
        entity.setCmImage("non");
        return catMessageRepository.save(entity);
    }

    //
    public ChatMessage deleteChatMessageById(String id, String create, String receiver) throws BaseException {
        /// validate
        if (Objects.isNull(id)) {
            throw ChatException.deleteFail();
        }
        if (Objects.isNull(create)) {
            throw ChatException.deleteFail();
        }
        if (Objects.isNull(receiver)) {
            throw ChatException.deleteFail();
        }
         /// verify
        Optional<ChatMessage> opt = catMessageRepository.findById(id);
        if(opt.isEmpty()){
            throw ChatException.deleteFail();
        }
        ChatMessage entity = opt.get();

        if (!entity.getCmCreated().toString().equals(create)){
            throw ChatException.deleteFail();
        }
        if (!entity.getChatRoomId().equals(receiver)){
            throw ChatException.deleteFail();
        }

        /// update message
        entity.setCmStatus(false);

        return catMessageRepository.save(entity);
    }


}
