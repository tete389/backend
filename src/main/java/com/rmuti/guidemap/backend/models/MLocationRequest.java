package com.rmuti.guidemap.backend.models;

import lombok.Data;

import java.io.File;

@Data
public class MLocationRequest {
    
    private String name;

    private String address;

    private double latitude;

    private double longitude;

    private File image;

    private String detail;

}
