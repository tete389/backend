package com.rmuti.guidemap.backend.controller;

import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.exception.ChatException;
import com.rmuti.guidemap.backend.models.ChatMessage;
import com.rmuti.guidemap.backend.models.request.ChatMessageRequest;
import com.rmuti.guidemap.backend.util.SecurityUtil;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ChatMessageController {

    private final SimpMessagingTemplate template;

    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void post(ChatMessageRequest request) throws BaseException {
        Optional<String> currentUserId = SecurityUtil.getCurrentUserId();
        if (currentUserId.isEmpty()){
            throw ChatException.accessDenied();
        }

        final String destination = "chat";

        ChatMessage payload = new ChatMessage();
        payload.setFrom(currentUserId.get());
        payload.setMessage(request.getMassage());

        template.convertAndSend(destination, payload);
    }
}
