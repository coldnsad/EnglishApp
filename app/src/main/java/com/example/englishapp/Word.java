package com.example.englishapp;

public class Word {

    private final String name;
    //private final String image;
    private final int id;

    public Word(String name, int id) {
        this.name  = name;
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }
}

