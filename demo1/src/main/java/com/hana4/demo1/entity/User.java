package com.hana4.demo1.entity;

import java.util.Set;

import org.hibernate.annotations.Comment;

import com.hana4.demo1.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DemoUser")
@Getter
@Setter
@ToString
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(columnDefinition = "bigint unsigned")
		private Long id;

		@Column(name = "username", nullable = false, columnDefinition = "varchar(31)")
		@Comment("사용자 이름")
		private String name;

		private short age;

		@Transient
		private int auth;

		public User() {
				this("");
		}

		public User(String name) {
				this(name, (short)0);
		}

		public User(Long id, String name) {
				this(id, name, (short)0);
		}

		public User(String name, short age) {
				this(0L, name, age);
		}

		public User(Long id, String name, short age) {
				this.id = id;
				this.name = name;
				this.age = age;
		}

		public UserDTO toDTO() {
				return UserDTO.builder().id(id).name(name).age(age).build();
		}

		@ManyToMany(mappedBy = "codeUsers", fetch = FetchType.LAZY)
		@ToString.Exclude
		private Set<Code> useCodes;
}
