package com.rmuti.guidemap.backend.exception;

public class ChatException extends BaseException{

    public ChatException(String code) {
        super("chat."+ code);
       
    }

    public static ChatException accessDenied() {
        return new ChatException("access.denied");
    }

    public static ChatException unAuthorized() {
        return new ChatException("unauthorized");
    }

    public static ChatException sendMessageFail() {
        return new ChatException("sendMessage.fail");
    }

    public static ChatException messageNotFound() {
        return new ChatException("message.notFound");
    }

    public static ChatException updateFail() {
        return new ChatException("update.fail");
    }

    public static ChatException deleteFail() {
        return new ChatException("delete.fail");
    }

    public static ChatException failNameNull() {
        return new ChatException("fail.name.null");
    }

    public static ChatException failLocationNull() {
        return new ChatException("fail.location.null");
    }

    public static ChatException failDataNull() {
        return new ChatException("fail.data.null");
    }


    public static ChatException ChatRoomCreateFail() {
        return new ChatException("ChatRoom.fail.create");
    }

    public static ChatException failDuplicated() {
        return new ChatException("fail.duplicate");
    }

    public static ChatException ChatRoomFailDataNull() {
        return new ChatException("ChatRoom.fail.data.null");
    }


}
