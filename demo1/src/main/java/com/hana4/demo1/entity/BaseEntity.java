package com.hana4.demo1.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    // @CreatedDate
    // @Column(nullable = false, updatable = false)
    // private LocalDateTime createdAt;
    //
    // @LastModifiedDate
    // @Column(nullable = false, updatable = false)
    // private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updateAt;

}