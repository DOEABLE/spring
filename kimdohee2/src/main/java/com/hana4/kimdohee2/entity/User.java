package com.hana4.kimdohee2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(name = "user_name_unique", columnNames = "name")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, length = 36)
    @Comment("작성자ID")
    private String id;

    @Column(name = "name", nullable = false, length = 31, unique = true)
    private String name;

    @Column(name = "email", length = 255)
    private String email;
}
