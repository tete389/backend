package com.rmuti.guidemap.backend.exception;

public class LocationException extends BaseException{

    public LocationException(String code) {
        super("location."+ code);
       
    }

    public static LocationException locationFailNameNull() {
        return new LocationException("location.fail.title.null");
    }

    public static LocationException locationCreateFail() {
        return new LocationException("location.fail.create");
    }

    public static LocationException locationDeleteFail() {
        return new LocationException("location.fail.delete");
    }

    public static LocationException locationUpdateFail() {
        return new LocationException("location.fail.update");
    }

    public static LocationException locationFailDuplicated() {
        return new LocationException("location.fail.duplicate");
    }

    public static LocationException locationFailDataNull() {
        return new LocationException("location.fail.data.null");
    }

    public static LocationException accessDenied() {
        return new LocationException("access.denied");
    }


}
