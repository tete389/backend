package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.UserData;
import lombok.Data;

@Data
public class MUserProfileRequest {
    
    private String name;

    private String status;

    private String image;

}
