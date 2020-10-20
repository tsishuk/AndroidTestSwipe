package com.example.firstapp;

public class Note {
    private String name;
    private String Descritpion;
    private int colorId;

    public Note(String name, String descritpion, int colorId) {
        this.name = name;
        Descritpion = descritpion;
        this.colorId = colorId;
    }

    public String getDescritpion() {
        return Descritpion;
    }

    public int getColorId() {
        return colorId;
    }

    public String getName() {
        return name;
    }
}
