package com.rmuti.guidemap.backend.exception;

public class FileException extends BaseException{

    public FileException(String code) {
        super("file."+ code);
    }

    // check file null 
    public static FileException fileNull() {
        return new FileException("file.null"); 
    }

    // check file MaxSize
    public static FileException fileMaxSize() {
        return new FileException("file.MaxSize"); 
    }

     // check file no support
     public static FileException fileUnSupport() {
        return new FileException("file.can't.supportType");
    }
    
    
}
