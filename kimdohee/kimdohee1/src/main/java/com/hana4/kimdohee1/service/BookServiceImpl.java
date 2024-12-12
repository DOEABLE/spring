package com.hana4.kimdohee1.service;

import com.hana4.kimdohee1.dao.BookRepository;
import com.hana4.kimdohee1.dto.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }
    public List<Book> getList(){//전체리스트
        return repository.getList();
    }

    @Override
    public Book find(Integer bno) {
        return repository.find(bno);
    }

    @Override
    @Transactional
    public boolean borrowBook(Integer bno, String borrowerId) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusWeeks(1);

        return repository.updateBorrower(bno, borrowerId, startDate, endDate) > 0;
    }

    @Override
    public List<Book> getBorrowResult(String borrowerId) {
        return repository.getBorrowResult(borrowerId);
    }

    public void addBook(Book book) {
        repository.insert(book);
    }
    public void modify(Book book) {
        repository.update(book);
    }
    public void remove(Integer bno) {
        repository.delete(bno);
    }//controller에서 사용

    @Override
    public void updateBookStatus(Integer bno) {

    }

    @Override
    public boolean borrowBook(Integer bno, String borrowerId, LocalDateTime startDate, LocalDateTime endDate) {
        try {
            // 대출 가능 여부 확인
            Book book = repository.find(bno);
            if (book.getAvailability() != 1) {
                return false;
            }
            // 대출 정보 업데이트
            return repository.updateBorrower(bno, borrowerId, startDate, endDate) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Book getBookDetail(String isbn) {
        return repository.selectBookByIsbn(isbn);
    }
    public void updateAvailability(Integer bno, Integer availability) {
        repository.updateAvailability(bno, availability);
    }
}
