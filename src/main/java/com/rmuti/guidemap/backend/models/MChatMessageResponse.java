package com.rmuti.guidemap.backend.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MChatMessageResponse {

    private List<Object> result = new ArrayList<>();

}