package com.example.englishapp;

public class Word {

    private String name;
    private String transcription;
    private String translation;

    private int id;
    //private String image;

    Word(){}

    Word(String name, String translation, String transcription, int id) {
        this.name  = name;
        this.transcription = transcription;
        this.translation = translation;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}

