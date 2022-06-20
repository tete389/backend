package com.rmuti.guidemap.backend.models;

import lombok.Data;

@Data
public class MChatMessageDeleteRequest {

    private String id;

    private String created;

    private String receiver;

}