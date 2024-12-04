package com.hana4.demo1.domain;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "DemoUser")
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(columnDefinition = "bigint unsigned")
		private Long id;
		@Column(name = "username", nullable = false, columnDefinition = "varchar(31)")
		@Comment("사용자 이름")
		private String name;

		private short age;
		@Transient//실제 테이블 컬럼은 아니지만 멤버변수로만 쓰겠다.
		private int auth;

		public User() {
			this("");
		}

		public User(String name) {
				this(name, (short)0);
		}
	public User(Long id, String name) {
		this(id,name, (short)0);
	}
	public User(String name, short age) {
		this(0L, name,age);
	}

		public User(Long id, String name, short age) {
				this.id = id;
				this.name = name;
				this.age = age;
		}

		public void setId(Long id) {
				this.id = id;
		}

		public void setName(String name) {
				this.name = name;
		}

		public Long getId() {
				return id;
		}

		public String getName() {
				return name;
		}
}
