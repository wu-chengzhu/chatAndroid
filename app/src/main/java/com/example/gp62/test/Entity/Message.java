package com.example.gp62.test.Entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Message implements Serializable,Comparable<Message> {
    private  int sender;
    private  int receiver;
    private String sendName;

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    private String msg;


    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", sendName='" + sendName + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }


    @Override
    public int compareTo(@NonNull Message msg) {
        return (this.getSender()-msg.getSender());
    }
}
