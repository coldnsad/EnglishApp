package com.example.englishapp;

public class WordCategory {

    private final String name;
    private final String image;
    private final int id;

    public WordCategory(String name, String image, int id) {
        this.image = image;
        this.name  = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public int getId(){
        return id;
    }
}
