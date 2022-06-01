package com.example.englishapp;

public class User {
    private String email, name, secondName;

    User(){}

    User(String email, String name, String secondName){
        this.email = email;
        this.name = name;
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
