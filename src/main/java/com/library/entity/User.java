package com.library.entity;

import lombok.*;

@Data
@AllArgsConstructor
public class User {
    private String userName;
    private int userAge;
    private Long userId;

    @Override
    public String toString() {
        return "Book name: " + this.userName;
    }
}
