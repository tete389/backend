package com.rmuti.guidemap.backend.exception;

public class BuildingException extends BaseException{

    public BuildingException(String code) {
        super("building."+ code);
       
    }

    public static AuthException buildingFailTitleNull() {
        return new AuthException("building.fail.title.null");
    }

    public static BuildingException buildingFailDuplicated() {
        return new BuildingException("building.fail.duplicate");
    }


}
