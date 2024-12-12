package com.hana4.kimdohee2.repository;

import com.hana4.kimdohee2.entity.Post;
import com.hana4.kimdohee2.entity.User;
import jakarta.persistence.EntityManager;
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

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest //[todo]위, 아래 둘 다 통과.
@Transactional
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager em;
    @Test
    @DisplayName("Post 작성 테스트")
    public void saveNewPostTest() {
        // given
        User writer = new User();
        writer.setName("TestWriter");
        writer.setEmail("testwriter@example.com");
        em.persist(writer); // 작성자를 먼저 저장

        Post post = new Post();
        post.setTitle("Sample Post Title");
        post.setBody("This is the body of the sample post.");
        post.setWriter(writer); // 작성자를 연결

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertThat(savedPost.getId()).isNotNull(); // 저장된 게시글 ID 확인
        assertThat(savedPost.getTitle()).isEqualTo("Sample Post Title");
        assertThat(savedPost.getBody()).isEqualTo("This is the body of the sample post.");
        assertThat(savedPost.getWriter()).isEqualTo(writer); // 작성자 확인
    }

    @Test
    @DisplayName("Post 수정 테스트")
    public void updatePostTest() {
        // given
        User user = new User();
        user.setName("TestUser");
        user.setEmail("testuser@example.com");
        em.persist(user); // TestEntityManager를 사용하여 User를 먼저 저장

        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(user);

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
        User user = new User();
        user.setName("TestUser");
        user.setEmail("testuser@example.com");
        em.persist(user); // TestEntityManager를 사용하여 User를 먼저 저장

        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("This is a test post.");
        post.setWriter(user);

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
        User writer = new User();
        writer.setName("TestUser");
        writer.setEmail("testuser@example.com");
        em.persist(writer); // TestEntityManager를 사용하여 User를 먼저 저장

        Post post1 = new Post();
        post1.setTitle("Post1");
        post1.setBody("Content1");
        post1.setWriter(writer);
        em.persist(post1);

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setBody("Content 2");
        post2.setWriter(writer);
        em.persist(post2);

        em.flush();
        // when
        List<Post> posts = postRepository.findAll();

        // then
        assertThat(posts).hasSize(2); // 저장된 Post가 2개인지 확인
        assertThat(posts.get(0).getTitle()).isEqualTo("Post1");
        assertThat(posts.get(1).getTitle()).isEqualTo("Post 2");
    }

    @Test
    @DisplayName("Post 상세 조회 테스트")
    public void postDetailTest(){
        //작성자 생성 및 저장
        User writer = new User();
        writer.setName("Kim11");
        writer.setEmail("Kim11@gmail.com");
        em.persist(writer);

        //게시글 생성 및 저장
        Post post = new Post();
        post.setTitle("Detailed Post");
        post.setBody("This is a detailed post content.");
        post.setWriter(writer);
        em.persist(post);

        em.flush();

        Optional<Post> retrievedPost = postRepository.findById(post.getId());

        assertThat(retrievedPost).isPresent();//게시글 존재 유무
        assertThat(retrievedPost.get().getTitle()).isEqualTo(post.getTitle());
        assertThat(retrievedPost.get().getBody()).isEqualTo(post.getBody());
        assertThat(retrievedPost.get().getWriter()).isEqualTo(writer);
        assertThat(retrievedPost.get().getWriter().getName()).isEqualTo(writer.getName());//"Kim4"
    }
    @Test
    @DisplayName("Post 작성자별 조회 테스트(선택)")
    public void findByWriterTest() {
        // given
        User writer = new User();
        writer.setName("Kim10");
        writer.setEmail("Kim10@gmail.com");
        em.persist(writer); // TestEntityManager를 사용하여 User를 저장
        // given
        Post post = new Post();
        post.setTitle("Test Post");
        post.setBody("Content");
        post.setWriter(writer);

        postRepository.save(post);

        // when
        List<Post> posts = postRepository.findByWriter(writer);

        // then
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getWriter()).isEqualTo(writer);
    }
}
