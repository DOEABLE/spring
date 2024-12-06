package com.hana4.kimdohee1.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
//@ToString
public class Book {
    private int bno, availability;
    private String title, author, publisher, discription, isbn, borrorId;
    private LocalDateTime startDate, endDate;

    @Override
    public String toString() {
        return "Book{" +
                "bno=" + bno +
                ", availability=" + availability +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", discription='" + discription + '\'' +
                ", isbn='" + isbn + '\'' +
                ", borrorId='" + borrorId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
