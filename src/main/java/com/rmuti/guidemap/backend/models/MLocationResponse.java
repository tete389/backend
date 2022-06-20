package com.rmuti.guidemap.backend.models;

import com.rmuti.guidemap.backend.table.LocationData;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class MLocationResponse {
    
    private List<LocationData> result = new ArrayList<>();

}
