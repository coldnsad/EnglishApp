package com.example.englishapp;

public class User {
    private String email, nickName;

    User(){}

    User(String email, String password, String nickName){
        this.email = email;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
