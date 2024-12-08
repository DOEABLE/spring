package com.hana4.kimdohee1.service;

import com.hana4.kimdohee1.dto.Book;

import java.time.LocalDateTime;
import java.util.List;


public interface BookService {
    List<Book> getList();
    Book find(Integer bno);
    boolean borrowBook(Integer bno, String borrowerId);
    List<Book> getBorrowResult(String borrowerId);
    void addBook(Book book);

    void remove(Integer bno);

    void updateBookStatus(Integer bno);

    boolean borrowBook(Integer bno, String borrowerId, LocalDateTime startDate, LocalDateTime endDate);
}
