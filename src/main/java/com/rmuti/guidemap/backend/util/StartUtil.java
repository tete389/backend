package com.rmuti.guidemap.backend.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StartUtil implements ApplicationListener<ApplicationReadyEvent> {

//    private void createDirIfNotExist(){
//
//
//    }
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        File fileFolderUpload = new File(FilesUtil.baseDir);
        File fileFolderUserProfileUrl = new File(FilesUtil.imageUserProfileUrl);
        File fileFolderChatMessageUrl = new File(FilesUtil.imageChatMessageUrl);
        File fileFolderChatRoomUrl= new File(FilesUtil.imageChatRoomUrl);
        File fileFolderLocationDataUrl= new File(FilesUtil.imageLocationDataUrl);
        dirIfNotExist(fileFolderUpload);
        dirIfNotExist(fileFolderUserProfileUrl);
        dirIfNotExist(fileFolderChatMessageUrl);
        dirIfNotExist(fileFolderChatRoomUrl);
        dirIfNotExist(fileFolderLocationDataUrl);


    }

    private void dirIfNotExist(File fileFolderUpload) {
        if (!fileFolderUpload.exists()){
            boolean mkdir = fileFolderUpload.mkdir();

        }
    }

}
