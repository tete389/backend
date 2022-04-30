package com.rmuti.guidemap.backend.api;

import com.rmuti.guidemap.backend.controller.ChatMessageController;
import com.rmuti.guidemap.backend.exception.BaseException;
import com.rmuti.guidemap.backend.models.request.ChatMessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApi {

    private final ChatMessageController chatMessage;

    public ChatApi(ChatMessageController chatMessage) {
        this.chatMessage = chatMessage;
    }

    @PostMapping("/massage")
    public ResponseEntity<Void> post(@RequestBody ChatMessageRequest request) throws BaseException {
        chatMessage.post(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
