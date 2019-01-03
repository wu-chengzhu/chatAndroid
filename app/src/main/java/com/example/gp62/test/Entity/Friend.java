package com.example.gp62.test.Entity;

public class Friend {
    private int id;
    private String friendName;

    public Friend(int id,String friendName)
    {
        this.id=id;
        this.friendName=friendName;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
