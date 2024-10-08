package com.example.messengerandroid.model;

public class Chat {
    private String senderName;
    private String lastMessage;
    private String time;
    private int profileImage;

    public Chat(String senderName, String lastMessage, String time, int profileImage) {
        this.senderName = senderName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.profileImage = profileImage;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }

    public int getProfileImage() {
        return profileImage;
    }
}

