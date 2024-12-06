package com.hana4.kimdohee1.dao;

import com.hana4.kimdohee1.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookRepository {
    List<Book> getList();
    Book find(Integer bno);
    void insert(Book book);
    void delete(int bno);
    void update(Book book);
}
