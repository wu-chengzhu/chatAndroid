package com.example.gp62.test.Entity;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private Friend friend;
    private ArrayList<String> messageList;

    /**
     * 构造函数
     * @param friend  这个朋友的信息
     * @param messageList 这个人发来的消息
     */
    public MessageList(Friend friend,ArrayList<String> messageList)
    {
        this.friend=friend;
        this.messageList=messageList;
    }



    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<String> messageList) {
        this.messageList = messageList;
    }






}
