package com.library;

import com.library.entity.Book;
import com.library.entity.User;
import com.library.service.LibraryServiceImpl;


import java.util.*;

import static java.lang.System.out;

public class ImportMockData {
    public static void main(String[] args) {

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
        for(User userIterator : userList){
            if(!userIterator.getUserName().equals("Admin")){
                List<Book> newBookList = new ArrayList<>(){};
                testService.getLibraryMap().put(userIterator, newBookList);
            }
        }
            testService.showMap();
        out.println("Get All Books: ");
            testService.getAllBooks().forEach(book -> out.println("\t" + book.toString()));
        out.println("Get User Book: ");
            testService.getUserBook(0L).forEach(book -> out.println("\t" + book.toString()));
        out.println("Get Available Book: ");
            testService.getAvailableBook().forEach(book -> out.println("\t" + book.toString()));
        out.println("Take book: ");
            testService.takeBook(2L, 2L);
            testService.showMap();
        out.println("Return book: ");
            testService.returnBook(0L, 2L);
            testService.showMap();

        out.println(testService.getLibraryMap().entrySet().iterator().next().getKey());
        ArrayList<Book> checkedBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> entry : testService.getLibraryMap().entrySet()){
            if(entry.getKey().getUserId().equals(0l))
                checkedBookList.addAll(entry.getValue());
        }

    }
}
