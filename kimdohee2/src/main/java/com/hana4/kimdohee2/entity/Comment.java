package com.hana4.kimdohee2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createAt", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updateAt", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "post", nullable = false, foreignKey = @ForeignKey(name = "comment_post_foreign"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "writer", nullable = false, foreignKey = @ForeignKey(name = "comment_writer_foreign"))
    private User writer;

    @Column(name = "body", nullable = false, length = 500)
    private String body;
}

