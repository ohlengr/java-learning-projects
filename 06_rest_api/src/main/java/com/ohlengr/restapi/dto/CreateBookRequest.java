package com.ohlengr.restapi.dto;

public class CreateBookRequest {
    private final String title;
    private final String author;

    public CreateBookRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
