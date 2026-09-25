package com.ohlengr.restapi.model;

public class Message {
    private final String text;
    private final String author;

    public Message(String text, String author){
        this.text = text;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}
