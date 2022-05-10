package com.example.englishapp;

public class Word {

    private final String name;
    private final String image;

    public Word(String name, String image) {
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

