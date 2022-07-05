package com.rmuti.guidemap.backend.models;

import lombok.Data;

@Data
public class MChatMessageDeleteRequest {

    private String cmId;

    private String cmCreated;

    private String cmReceiver;

}