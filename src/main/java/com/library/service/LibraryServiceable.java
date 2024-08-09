package com.library.service;

import com.library.entity.Book;

import java.util.List;

public interface LibraryServiceable {
    List<Book> getAllBooks();
    List<Book> getAvailableBook();
    List<Book> getUserBook(Long userId);
    void takeBook(Long userId, Long bookId);
    void returnBook(Long userId, Long bookId);
}
