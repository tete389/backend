package com.rmuti.guidemap.backend.models;

import lombok.Data;

@Data
public class MRegisterRequest {

    private String name;

    private String email;

    private String password;

    //private String passWordAdmin;

}
