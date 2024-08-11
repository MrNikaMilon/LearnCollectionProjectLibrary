package com.library.service;

import static java.lang.System.*;

import com.library.entity.Book;
import com.library.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class LibraryServiceImpl implements LibraryServiceable {
    private List<Book> bookList;
    private List<User> userList;
    @Getter
    private HashMap<User, List<Book>> libraryMap;

    public LibraryServiceImpl(List<Book>bookList, List<User>userList) {
        this.bookList = bookList;
        this.userList = userList;
        libraryMap = new HashMap<>();
        libraryMap.put(new User("Admin", 0, 0L), bookList);
        //addBookListToUser();
    }

    @Override
    public List<Book> getAllBooks(){
        return this.bookList;
    }

    @Override
    public List<Book> getAvailableBook() {
        for(Map.Entry<User, List<Book>> iterator : libraryMap.entrySet()){
            if(iterator.getKey().getUserId().equals(0L)){
                return iterator.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Book> getUserBook(Long userId) {
        for(Map.Entry<User, List<Book>> iterator : libraryMap.entrySet()){
            if(iterator.getKey().getUserId().equals(userId)){
                return iterator.getValue();
            }
        }
        return null;
    }
    @SneakyThrows
    @Override
    public void takeBook(Long userId, Long bookId)  {
        ArrayList<Book> takeBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> iterator : libraryMap.entrySet()){
            for(Book book : iterator.getValue()){
                if(!iterator.getKey().getUserName().equals("Admin") && book.getBookId().equals(bookId)){
                    throw new Exception("Book in another user list!");
                } else {
                    if(book.getBookId().equals(bookId)){
                        takeBookList.add(book);
                        iterator.getValue().removeIf(removedElement -> removedElement.getTitle().equals(takeBookList.iterator().next().getTitle()));
                        break;
                    }
                }
            }
        }
        libraryMap.forEach((key, value) -> {
            if (key.getUserId().equals(userId)) {value.addAll(takeBookList);}});
    }

    @SneakyThrows
    @Override
    public void returnBook(Long userId, Long bookId) {
        ArrayList<Book> returnBookList = new ArrayList<>();
        for(Map.Entry<User, List<Book>> iterator : libraryMap.entrySet()){
            for(Book book : iterator.getValue()){
                if(iterator.getKey().getUserId().equals(userId) && book.getBookId().equals(bookId)){
                    throw new Exception("Book was return!");
                } else {
                    if(book.getBookId().equals(bookId)){
                        returnBookList.add(book);
                        iterator.getValue().removeIf(removedElement -> removedElement.getTitle().equals(returnBookList.iterator().next().getTitle()));
                        break;
                    }
                }
            }
        }

        libraryMap.forEach((key, value) -> {
            if(key.getUserId().equals(userId)){value.addAll(returnBookList);}});

    }
}
