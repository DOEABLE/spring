package com.hana4.kimdohee2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @CreationTimestamp
    @Column(name = "createAt", nullable = false, updatable = false, columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP") //상단은 database 상 column명
    @org.hibernate.annotations.Comment("등록일시")
    private LocalDateTime createAt;                                 //하단은 java상 명시.

    @UpdateTimestamp
    @Column(name = "updateAt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Comment("수정일시")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "post", nullable = false, foreignKey = @ForeignKey(name = "comment_post_foreign"))
    private Post post;

    @ManyToOne
    @JoinColumn(name = "writer", nullable = false, foreignKey = @ForeignKey(name = "comment_writer_foreign"))
    @org.hibernate.annotations.Comment("작성자ID")
    private User writer;

    @Column(name = "body", nullable = false, length = 500)
    private String body;

    public Comment(String body, Post post, User user) {
        this.body=body;
        this.post=post;
    }
}

