package com.library;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.LibraryServiceImpl;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    ArrayList<Book> bookList = new ArrayList<>(List.of(
            new Book("The Elven Prophecy", "King",1980,1L),
            new Book("The Quest for the Phoenix Stone", "King",1980,2L),
            new Book("The Crystal of Destiny", "King",1980,3L),
            new Book("The Sorcerer's Tome", "King",1980,4L),
            new Book("The Legends of Mythos", "King",1980,5L)));

    List<User> userList = Arrays.asList(
            new User("Nikon", 12,1L),
            new User("Admin", 16,0L),
            new User("Dmitry", 22,2L));
    LibraryServiceImpl testService = new LibraryServiceImpl(bookList, userList);



    @DisplayName("Test return all book in our library")
    @Test
    void testReturnAllBook() {
        Assertions.assertEquals(bookList, testService.getAllBooks());
        System.out.println("Successfully retrieved all books");
    }

    @DisplayName("Test return user book")
    @Test
    void testGetUserBook() {
        ArrayList<Book> checkedBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> entry : testService.getLibraryMap().entrySet()){
            if(entry.getKey().getUserId().equals(0l))
                checkedBookList.addAll(entry.getValue());
        }
        Assertions.assertEquals(checkedBookList, testService.getUserBook(0l));
        System.out.println("Successfully retrieved all users books, we returned next book list: " + checkedBookList);
    }
    @DisplayName("Test return user book")
    @Test
    void testGetUserBookWithUser() {
        ArrayList<Book> checkedBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> entry : testService.getLibraryMap().entrySet()){
            if(entry.getKey().getUserId().equals(0l))
                checkedBookList.addAll(entry.getValue());
        }
        Assertions.assertEquals(checkedBookList, testService.getUserBook(0l));
        System.out.println("Successfully retrieved all users books, we returned next book list: " + checkedBookList);
    }

    @DisplayName("Single test successful")
    @Test
    void getAvailableBook() {

    }

    @DisplayName("Single test successful")
    @Test
    void testTakeBook() {
        testService.getLibraryMap().put(userList.get(2), new ArrayList<>());
        testService.showMap();
        Long userIdForTest = 2L;
        Long bookIdForTest = 3L;
        testService.takeBook(userIdForTest, bookIdForTest);
        var testing = testService.getLibraryMap().entrySet()
                .stream()
                .filter(map -> map.getKey().getUserId().equals(2L))
                .findFirst().map(map -> {
                    var key = map.getKey().getUserId();
                    var value = map.getValue();
                    return key + "" + value.stream().findFirst().get().getBookId();
                });
        System.out.println(testing.get());
        //Assertions.assertEquals(mapForTested, testService.getLibraryMap().get(userList.get(2)));


    }

    @DisplayName("Single test successful")
    @Test
    void testReturnBook() {

    }

}