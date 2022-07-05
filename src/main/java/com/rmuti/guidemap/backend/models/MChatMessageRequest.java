package com.rmuti.guidemap.backend.models;

import lombok.Data;

import java.util.Date;

@Data
public class MChatMessageRequest {

    private String cmReceiver;

    private String cmMessage;

}