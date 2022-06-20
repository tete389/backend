package com.rmuti.guidemap.backend.business;


import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.MChatMessageRequest;
import com.rmuti.guidemap.backend.models.MChatMessageDeleteRequest;
import com.rmuti.guidemap.backend.models.MChatMessageResponse;
import com.rmuti.guidemap.backend.models.MChatRoomRequest;
import com.rmuti.guidemap.backend.repository.ChatMessageRepository;
import com.rmuti.guidemap.backend.services.ChatMessageService;
import com.rmuti.guidemap.backend.services.TokenService;
import com.rmuti.guidemap.backend.table.ChatMessage;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChatMessageBusiness {

    private final ChatMessageService chatMessageService;

    private final TokenService tokenService;


    ///
    public String createChatMessage(MChatMessageRequest request) throws BaseException {
        UserProfile resToken = tokenService.checkTokenUser();

        ChatMessage cmRes = chatMessageService.createChatMessage(
                resToken.getUpId(),
                request.getReceiver(),
                request.getMessage()
        );
        Optional<ChatMessage> opt = chatMessageService.findById(cmRes.getCmId());
        if(opt.isEmpty()){
            throw ChatException.sendMessageFail();
        }
        return "Send Message Complied";
    }

    ///
    public MChatMessageResponse getChatMessageByChatRoomId(MChatRoomRequest request) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        MChatMessageResponse chatMessages = chatMessageService.findMessageByChatRoomId(request.getCrId());
        if(chatMessages == null){
            throw ChatException.messageNotFound();
        }
        return chatMessages;
    }

    ///
    public String deleteChatMessageById(MChatMessageDeleteRequest request) throws BaseException{
        UserProfile resToken = tokenService.checkTokenUser();

        ChatMessage res =  chatMessageService.deleteChatMessageById(
                request.getId(),
                request.getCreated(),
                request.getReceiver()
        );
        Optional<ChatMessage> opt = chatMessageService.findById(res.getCmId());
        if(opt.isEmpty()){
            throw ChatException.deleteFail();
        }
        if (opt.get().isCmStatus()){
            throw ChatException.deleteFail();
        }
        return "Message is Deleted";
    }
}
