package com.hana4.kimdohee2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post /*extends BaseEntity*/{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint unsigned")
    private Long id;

    @CreationTimestamp
    @Column(name = "createAt", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Comment("등록일시")
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "updateAt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Comment("수정일시")
    private LocalDateTime updateAt;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @ManyToOne      //n쪽(Post)에 FK 칼럽을 writer로 지정
    @JoinColumn(name = "writer", nullable = false, foreignKey = @ForeignKey(name = "post_writer_foreign"))//외래키 제약조건에 이름지정
    @org.hibernate.annotations.Comment("작성자 ID")
    private User writer;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;


    public Post(String title, User writer, String body) {
        this.title=title;
        this.writer = writer;
        this.body=body;
    }
    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
