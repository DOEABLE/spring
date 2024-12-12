package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Comment;
import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
@Transactional
public class CommentRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("Comment 추가 테스트")
    public void addCommentTest() {
        // given
        // 작성자 생성 및 저장
        User postWriter = new User();
        postWriter.setName("PostWriter");
        postWriter.setEmail("postwriter@example.com");
        em.persist(postWriter);

        // 게시글 생성 및 저장
        Post post = new Post();
        post.setTitle("Post with Comments");
        post.setBody("This is a post to test adding comments.");
        post.setWriter(postWriter);
        em.persist(post);

        // 댓글 작성자 생성 및 저장
        User commentWriter = new User();
        commentWriter.setName("CommentWriter");
        commentWriter.setEmail("commentwriter@example.com");
        em.persist(commentWriter);

        // 댓글 생성
        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(commentWriter);
        comment.setPost(post);

        // when
        Comment savedComment = commentRepository.save(comment); // 댓글 저장
        em.flush(); // DB에 반영

        // then
        assertThat(savedComment).isNotNull(); // 저장된 댓글이 null이 아님을 확인
        assertThat(savedComment.getId()).isNotNull(); // 저장된 댓글 ID가 생성되었는지 확인
        assertThat(savedComment.getBody()).isEqualTo("This is a test comment."); // 댓글 내용 확인
        assertThat(savedComment.getWriter()).isEqualTo(commentWriter); // 댓글 작성자 확인
        assertThat(savedComment.getPost()).isEqualTo(post); // 댓글이 올바른 게시글에 연결되었는지 확인
    }

    @Test
    @Transactional
    @DisplayName("Comment 수정 테스트")
    public void updateCommentTest() {
        // given
        User postWriter = new User();
        postWriter.setName("Kim7");
        em.persist(postWriter);

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

        //when
        savedComment.setBody("Updated comment");    //댓글 내용 수정
        em.flush();                                 //변경 사항 반영

        // then
        Comment updatedComment = em.find(Comment.class, comment.getId());
        assertThat(updatedComment.getBody()).isEqualTo("Updated comment");
    }


    @Test
    @DisplayName("Comment 삭제 테스트")
    public void deleteCommentTest() {
        // given
        User writer = new User();
        writer.setName("Writer");
        em.persist(writer);

        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(writer);
        em.persist(post);

        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(writer);
        comment.setPost(post);
        em.persist(comment);
        em.flush(); // 데이터베이스에 반영

        // when
        commentRepository.delete(comment);

        // then
        Optional<Comment> deletedComment = commentRepository.findById(comment.getId());
        assertThat(deletedComment).isEmpty(); // 삭제되었음을 검증
    }
    @Test
    @Transactional
    @DisplayName("Comment 저장 테스트")              //post에 딸린 comment목록 조회...comment없는 post는 제외.
    public void saveCommentTest() {
        // given
        // 작성자 및 게시글 저장
        User postWriter = new User();
        postWriter.setName("Kim6");
        em.persist(postWriter);

        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(postWriter);
        em.persist(post);

        //댓글 작성자 및 댓글 저장
        User commentWriter = new User();
        commentWriter.setName("Kim8");
        commentWriter.setEmail("Kim8@gmail.com");
        em.persist(commentWriter);

        Comment comment = new Comment();
        comment.setBody("This is a test comment.");
        comment.setWriter(commentWriter);
        comment.setPost(post);
        // when
        Comment savedComment = commentRepository.save(comment);

        // then
        assertThat(savedComment.getId()).isNotNull();                       //저장된 댓글 id
        assertThat(savedComment.getBody()).isEqualTo(comment.getBody());
        assertThat(savedComment.getWriter()).isEqualTo(commentWriter);      //댓글 작성자 확인
        assertThat(savedComment.getPost()).isEqualTo(post);                 //댓글이 달린 게시글 확인
    }
}
