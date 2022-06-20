package com.rmuti.guidemap.backend.exception;

public class ChatRoomException extends BaseException{

    public ChatRoomException(String code) {
        super("ChatRoom."+ code);
       
    }




}
