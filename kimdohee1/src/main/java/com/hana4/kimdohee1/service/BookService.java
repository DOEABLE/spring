package com.hana4.kimdohee1.service;

import com.hana4.kimdohee1.dao.BookRepository;
import com.hana4.kimdohee1.dto.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public List<Book> getList(){//전체리스트
        return repository.getList();
    }
    public void addCust(Book book) {
        repository.insert(book);
    }
    public Book find(int bno) {
        return repository.find(bno);
    }
    public void modify(Book book) {
        repository.update(book);
    }
    public void remove(Integer bno) {
        repository.delete(bno);
    }//controller에서 사용
}
