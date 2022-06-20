package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;

@Data
public class MUserDataResponse {
    
    private String email;

    private String role;

    private String userProfile;

}
