package com.example.englishapp;

public class WordCategory {

    private final String name;
    private final String image;

    public WordCategory(String name, String image) {
        this.image = image;
        this.name  = name;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }
}
