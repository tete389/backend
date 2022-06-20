package com.rmuti.guidemap.backend.models;

import lombok.Data;

import java.util.Date;

@Data
public class MChatMessage {

    private String sender;

    private String roomName;

    private String message;

    private Date created;

    public MChatMessage() {
        created = new Date();
    }
}
