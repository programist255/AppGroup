package com.example.myapplication.Pag;

public class Chat {
    private String Sender;
    private String Receiver;
    private String Message;

    Chat( String Message, String Receiver, String Sender){
            this.Sender = Sender;
            this.Receiver = Receiver;
            this.Message = Message;
    }

    public Chat(){}
    public String getSender() {
        return Sender;
    }
    public void setSender(String Sender) {
        this.Sender =  Sender;
    }

    public String getReceiver() {
        return Receiver;
    }
    public void setReceiver(String Receiver) {
        this.Receiver = Receiver;
    }

    public String getMessage() {
        return Message;
    }
    public void setMessage(String Message) {
        this.Message =  Message;
    }
}


