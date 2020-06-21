package com.example.myapplication.Pag;

public class Group {
    private String NameGroup;
    private String Photo;

    public Group(String NameGroup, String Photo){
        this.NameGroup = NameGroup;
        this.Photo = Photo;
    }

    public String getName() {
        return NameGroup;
    }

    public String getImage() {
        return Photo;
    }

}
