package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.UserData;
import lombok.Data;

import java.io.File;

@Data
public class MUserProfileResponse {

    private String profileId;
    
    private String name;

    private String status;

    private String image;

    //private UserData user;

  
}
