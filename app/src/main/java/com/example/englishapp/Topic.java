package com.example.englishapp;

public class Topic {

    private final String name;
    private final int serialNumber;

    public Topic(String name, int serialNumber) {
        this.serialNumber = serialNumber;
        this.name  = name;
    }

    public String getName(){
        return name;
    }

    public String getSerialNumber(){
        return String.valueOf(serialNumber);
    }
}

