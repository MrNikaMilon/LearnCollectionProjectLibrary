package com.library;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.LibraryServiceImpl;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    LibraryServiceImpl testService;
    @BeforeEach
    void createMockDataForTest() {
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

        testService = new LibraryServiceImpl(bookList, userList);
    }


    @DisplayName("Tested return all book in this library")
    @Test
    void testReturnAllBookFromTestedLibrary() {
        Assertions.assertEquals(testService.getBookList(), testService.getAllBooks());
        out.println("Test successful!");
    }

    @DisplayName("Tested return user book with Admin account")
    @Test
    void testGetBookListWithAdminAccount() {
        ArrayList<Book> checkedBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> entry : testService.getLibraryMap().entrySet()){
            if(entry.getKey().getUserId().equals(0L))
                checkedBookList.addAll(entry.getValue());
        }
        Assertions.assertEquals(checkedBookList, testService.getUserBook(0L));
        out.println("Test successful!");
    }

    @DisplayName("Tested return user book with User Account")
    @Test
    void testGetUserBookWithRandomUserAccount() {
        ArrayList<Book> checkedBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> entry : testService.getLibraryMap().entrySet()){
            if(entry.getKey().getUserId().equals(0L))
                checkedBookList.addAll(entry.getValue());
        }
        Assertions.assertEquals(checkedBookList, testService.getUserBook(0L));
        out.println("Test successful!");
    }


    @DisplayName("Tested get available book from library")
    @Test
    void getAvailableBookFromLibrary() {
        List<Long> testedBookId = new ArrayList<>(List.of(3L));
        testService.takeBook(2L, testedBookId.get(0));
        assertNotEquals(testedBookId, testService.getAvailableBook().stream().map(Book::getBookId).collect(Collectors.toList()));
        out.println("Test successful!");
    }


    @DisplayName("Tested method take book from library")
    @Test
    void testTakeBookForRandomUserAccount(){
        testService.getLibraryMap().put(testService.getUserList().get(2), new ArrayList<>());
        var userIdForTest = 2L;
        var bookIdForTest = 3L;
        var dataForCheck = userIdForTest + "" + bookIdForTest;
        testService.takeBook(userIdForTest, bookIdForTest);
        testService.takeBook(userIdForTest, 4L);
        var result = testService.getLibraryMap().entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .filter(book -> entry.getKey().getUserId().equals(userIdForTest) && book.getBookId().equals(bookIdForTest))
                        .map(book -> entry.getKey().getUserId() + "" + book.getBookId())
                )
                .distinct()
                .collect(Collectors.joining());
        Assertions.assertEquals(dataForCheck, result);
        out.println("Test successful!");
    }


    @DisplayName("Tested added taken book to admin user with exception")
    @Test
    void testExceptionTakeBook() {
        testService.getLibraryMap().put(testService.getUserList().get(2), new ArrayList<>());
        Exception exception = assertThrows(Exception.class, () -> {
            testService.takeBook(2L, 2L);
            testService.takeBook(3L, 2L);
        });
        assertEquals("Book in another user list!", exception.getMessage());
        out.println("Test successful!");
    }

    @DisplayName("Tested return book to admin user")
    @Test
    void testReturnBookFromRandomUserToAdmin() {
        testService.getLibraryMap().put(testService.getUserList().get(2), new ArrayList<>());
        var userForTest = 2L;
        var adminIdForTest = 0L;
        var bookIdForTest = 2L;
        testService.takeBook(userForTest, bookIdForTest);
        testService.returnBook(adminIdForTest, bookIdForTest);
        var result = testService.getLibraryMap().entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .filter(book -> entry.getKey().getUserId().equals(0L) && book.getBookId().equals(2L))
                        .map(book -> entry.getKey().getUserId() + "" + book.getBookId())
                )
                .distinct()
                .collect(Collectors.joining());
        var dataForCheck = adminIdForTest + "" + bookIdForTest;
        Assertions.assertEquals(dataForCheck, result);
        out.println("Test successful!");
    }

    @DisplayName("Tested added return book to admin user with exception")
    @Test
    void testedReturnBookFromRandomUserToAdminWithException(){
        testService.getLibraryMap().put(testService.getUserList().get(2), new ArrayList<>());
        testService.takeBook(2L, 2L);
        Exception exception = assertThrows(Exception.class, () -> {
            testService.returnBook(0L, 2L);
            testService.returnBook(0L, 2L);
        });
        assertEquals("Book was return!", exception.getMessage());
        out.println("Test successful!");
    }
}
