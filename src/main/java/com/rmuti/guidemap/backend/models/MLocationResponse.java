package com.rmuti.guidemap.backend.models;

import lombok.Data;

import java.io.File;

@Data
public class MLocationResponse {
    
    private String title;

    private String address;

    private double latitude;

    private double longitude;

    private File image;

    private String detail;

}
