package com.rmuti.guidemap.backend.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;

import java.io.File;


public class StartUtil {

    @EventListener(ApplicationReadyEvent.class)
    private void createDirIfNotExist(){

        dirIfNotExist(new File(FilesUtil.baseDir));
        dirIfNotExist(new File(FilesUtil.imageUserProfileUrl));
        dirIfNotExist(new File(FilesUtil.imageChatMessageUrl));
        dirIfNotExist(new File(FilesUtil.imageLocationDataUrl));
        System.out.print("commode ");

    }

    private void dirIfNotExist(File fileFolderUpload) {
        System.out.print("mkdir");
             if (!fileFolderUpload.exists()){
                 boolean mkdir = fileFolderUpload.mkdir();

             }
    }


}
