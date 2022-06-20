package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.ChatRoom;
import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MChatRoomResponse {

    private List<ChatRoom> result = new ArrayList<>();

}
