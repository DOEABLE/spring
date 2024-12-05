package com.hana4.kimdohee2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @ManyToOne      //n쪽(Post)에 FK 칼럽을 writer로 지정
    @JoinColumn(name = "writer", nullable = false, foreignKey = @ForeignKey(name = "post_writer_foreign"))//외래키 제약조건에 이름지정
    private User writer;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;
}

