package com.hana4.kimdohee1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
//@ToString
public class Book {
    private int bno, availability;
    private String title, author, publisher, description, isbn, borrowerId;
    private LocalDateTime startDate, endDate;

    @Override
    public String toString() {
        return "Book{" +
                "bno=" + bno +
                ", availability=" + availability +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", borrowerId='" + borrowerId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
