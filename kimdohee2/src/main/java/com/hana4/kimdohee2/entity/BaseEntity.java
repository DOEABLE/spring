package com.hana4.kimdohee2.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;

public class BaseEntity {
		@CreationTimestamp
		@Column(nullable = false, updatable = false, columnDefinition = "timestamp")
		@ColumnDefault("CURRENT_TIMESTAMP")
		private LocalDateTime createAt;

		@UpdateTimestamp
		@Column(nullable = false, columnDefinition = "timestamp")
		@ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
		private LocalDateTime updateAt;
}
