package com.example.englishapp;

public class Topic {

    private final String title;
    private final String body;
    private final String serialNumber;

    public Topic(String title, String body, String serialNumber) {
        this.title = title;
        this.body = body;
        this.serialNumber = serialNumber;
    }

    public String getTitle(){
        return title;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public String getBody(){
        return body;
    }
}

