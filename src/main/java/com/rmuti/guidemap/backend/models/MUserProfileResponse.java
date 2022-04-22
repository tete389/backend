package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.UserData;
import lombok.Data;

import java.io.File;

@Data
public class MUserProfileResponse {
    
    private String name;

    private String status;

    private File image;

    private UserData user;

  
}
