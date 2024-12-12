package com.hana4.kimdohee1.dao;

import com.hana4.kimdohee1.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper //mybatis, db와의 mapping
public interface BookRepository {
    // 목록 페이지
    List<Book> getList();
    // 조회 페이지
    Book find(Integer bno);
    void insert(Book book);
    void delete(@Param("bno")int bno);
    void update(@Param("book")Book book);

    // 대출 처리
    int updateBorrower(@Param("bno") Integer bno,
                       @Param("borrowerId") String borrowerId,
                       @Param("startDate") LocalDateTime startDate,
                       @Param("endDate") LocalDateTime endDate);
    Book selectBookByIsbn(String isbn);

    void updateAvailability(Integer bno, Integer availability);

    // 대출 결과 조회
    List<Book> getBorrowResult(String borrowerId);


}
