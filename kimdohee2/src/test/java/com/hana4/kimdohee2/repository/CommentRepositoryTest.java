package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Comment;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;
    User postWriter = new User();
    private final static String WRITER = "Kim1";

    @Test
    @DisplayName("Comment 저장 테스트")
    public void saveCommentTest() {
        // given
        User postWriter = new User();
        postWriter.setName("PostWriter");
        em.persist(postWriter);

        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(postWriter);
        em.persist(post);

        User commentWriter = new User();
        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(commentWriter);
        comment.setPost(post);

        // when
        Comment savedComment = commentRepository.save(comment);

        // then
        assertThat(savedComment.getId()).isNotNull();
        assertThat(savedComment.setBody()).isEqualTo(comment.getBody());
    }

    @Test
    @DisplayName("Comment 수정 테스트")
    public void updateCommentTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(postWriter);
        em.persist(post);

        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(postWriter);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        savedComment.setBody("Updated comment");

        // when
        Comment updatedComment = commentRepository.save(savedComment);

        // then
        assertThat(updatedComment.setBody()).isEqualTo("Updated comment");
    }

    @Test
    @DisplayName("Comment 삭제 테스트")
    public void deleteCommentTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter();
        em.persist(post);

        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(comment);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        // when
        commentRepository.delete(savedComment);

        // then
        Optional<Comment> deletedComment = commentRepository.findById(savedComment.getId());
        assertThat(deletedComment).isEmpty();
    }

    @Test
    @DisplayName("Comment 목록 조회 테스트")
    public void findAllCommentsTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter("TestUser");
        em.persist(post);

        Comment comment1 = new Comment();
        comment1.setBody("Test Comment 1");
        comment1.setWriter("User1");
        comment1.setPost(post);

        Comment comment2 = new Comment();
        comment2.setBody("Test Comment 2");
        comment2.setWriter("User2");
        comment2.setPost(post);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        // when
        List<Comment> comments = commentRepository.findByPost(post);

        // then
        assertThat(comments).hasSize(2);
    }
}
