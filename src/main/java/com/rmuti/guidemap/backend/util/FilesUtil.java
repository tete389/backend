package com.rmuti.guidemap.backend.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FilesUtil {

    private FilesUtil(){

    }

    public static String domain = "http://localhost:8080";

    public static String pathSet = "/";

    public static String baseDir = "upload";

    public static String imageUserProfileUrl = baseDir+"/imgUserProfile";
    public static String imageUserProfile = "/imgUserProfile";
    public static String imageChatMessageUrl = baseDir+"/imgChatMessage";
    public static String imageChatMessage = "/imgChatMessage";
    public static String imageChatRoomUrl = baseDir+"/imgChatRoom";
    public static String imageChatRoom = "/imgChatRoom";
    public static String imageLocationDataUrl = baseDir+"/imgLocationData";
    public static String imageLocationData = "/imgLocationData";

    public String ipAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
