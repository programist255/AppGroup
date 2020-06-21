package com.example.myapplication.Pag;

public class Task {

    private String Title;
    private String Text;

    public Task( String Title, String Text){
        this.Title = Title;
        this.Text = Text;
    }

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

}
