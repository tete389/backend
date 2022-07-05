package com.rmuti.guidemap.backend.repository;

import com.rmuti.guidemap.backend.table.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    List<ChatMessage> findByCmChatRoomIdOrderByCmCreatedAsc(String id);

    @Query(value = "SELECT cm.*, up.up_name, up.up_status, up.up_image FROM " +
            "chat_message cm JOIN user_profile up ON cm.cm_user_profile_id = up.up_id " +
            "WHERE cm.cm_chat_room_id = :chat_room_id ORDER BY cm.cm_created ASC",
            nativeQuery = true)
    List<ChatMessageResponse> findByChatRoomIdOrderByCreatedASC(@Param("chat_room_id")String id);

    public static interface ChatMessageResponse{
        String getCm_Id();

        String getCm_Created();

        String getCm_Message();

        boolean getCm_Status();

        String getCm_Type_Message();

        String getCm_Image();

        String getCm_Chat_Room_Id();

        String getCm_User_Profile_Id();

        String getUp_Name();

        String getUp_Status();

        String getUp_Image();
    }

}
