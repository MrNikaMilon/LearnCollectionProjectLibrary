package com.library.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int year;
    private Long bookId;

    @Override
    public String toString() {
        return "Book name: " + title;
    }
}
