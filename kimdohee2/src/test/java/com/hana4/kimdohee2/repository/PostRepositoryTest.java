package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Post;
import org.assertj.core.api.AbstractFileAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Post 저장 테스트")
    public void savePostTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post.setWriter("TestUser");

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("Test Post");
        assertThat(savedPost.getWriter()).isEqualTo("TestUser");
    }

    @Test
    @DisplayName("Post 수정 테스트")
    public void updatePostTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post.setWriter("TestUser");

        Post savedPost = postRepository.save(post);
        savedPost.setTitle("Updated Title");

        // when
        Post updatedPost = postRepository.save(savedPost);

        // then
        assertThat(updatedPost.getTitle()).isEqualTo("Updated Title");
    }

    @Test
    @DisplayName("Post 삭제 테스트")
    public void deletePostTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post.setWriter("TestUser");

        Post savedPost = postRepository.save(post);

        // when
        postRepository.delete(savedPost);

        // then
        Optional<Post> deletedPost = postRepository.findById(savedPost.getId());
        assertThat(deletedPost).isEmpty();
    }

    @Test
    @DisplayName("Post 목록 조회 테스트")
    public void findAllPostsTest() {
        // given
        Post post1 = new Post();
        post1.setTitle("Post 1");
        post1.setContent("Content 1");
        post1.setWriter("User1");

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setContent("Content 2");
        post2.setWriter("User2");

        postRepository.save(post1);
        postRepository.save(post2);

        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts).hasSize(2);
    }

    @Test
    @DisplayName("Post 작성자별 조회 테스트")
    public void findByWriterTest() {
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("Content");
        post.setWriter("TestUser");

        postRepository.save(post);

        // when
        List<Post> posts = postRepository.findByWriter("TestUser");

        // then
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getWriter()).isEqualTo("TestUser");
    }
}
