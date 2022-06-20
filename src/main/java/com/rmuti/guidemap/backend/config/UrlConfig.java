//package com.rmuti.guidemap.backend.config;
//
//import lombok.Data;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Data
//public class UrlConfig {
//
//    private String domain = "http://localhost:8080";
//
//    private String PathSet = "/";
//
//    private String baseDir = "upload";
//
//    private String imageUserProfileUrl = this.baseDir+"/imgUserProfile";
//    private String imageUserProfile = "/imgUserProfile";
//    private String imageChatMessageUrl = this.baseDir+"/imgChatMessage";
//    private String imageChatMessage = "/imgChatMessage";
//    private String imageChatRoomUrl = this.baseDir+"/imgChatRoom";
//    private String imageChatRoom = "/imgChatRoom";
//    private String imageLocationDataUrl = this.baseDir+"/imgLocationData";
//    private String imageLocationData = "/imgLocationData";
//
//    public String ipAddress() throws UnknownHostException {
//        return InetAddress.getLocalHost().getHostAddress();
//    }
//}
