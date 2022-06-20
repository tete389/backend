package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.UserProfile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MUserProfileResponse {

    private List<UserProfile> result = new ArrayList<>();

}
