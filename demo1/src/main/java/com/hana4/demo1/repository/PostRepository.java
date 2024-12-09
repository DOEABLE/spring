package com.hana4.demo1.repository;

import com.hana4.demo1.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,String> {
    List<Post> findByCreatedateLessThanEqual(LocalDateTime dateTime);   //지정된 날짜/시간 이전에 생성된 게시물 목록 조회

    long countByCreatedateLessThanEqual(LocalDateTime dateTime);        //지정된 날짜/시간 이전에 생성된 게시물 수 반환

    List<Post> findByWriter(String writer);

    List<Post> findByWriter(String writer, Sort sort);                  //특정 작성자의 게시물을 정렬하여 조회

    List<Post> findByTitleStartsWith(String title);                     //특정 문자열로 시작하는 제목의 게시물 조회

    List<Post> findByTitleStartingWith(String title);                   //특정 문자열로 시작하는 제목의 게시물 조회

    Page<Post> findByTitleLike(String title, Pageable pageable);        //제목에 특정 문자열이 포함된 게시물을 페이징하여 조회

    long countByTitleLike(String searchStr);                            //제목에 특정 문자열이 포함된 게시물 수 반환

    // @Query("select m, p from Msg m, Post p where m.post = p.id")
    @Query("""
				select p from Post p
				where p.writer = :writer and p.createdate <= :dateTime
		""")
    List<Post> findByOldWriter(@Param("writer") String writer, @Param("dateTime") LocalDateTime dateTime);//특정 작성자의 이전 게시물 조회

    @Query(value = "select p.title, p.writer, p.createdate from Post p where p.writer = :writer", nativeQuery = true)
    List<Object[]> findBySomeColumns(@Param("writer") String writer);   //특정 컬럼만 조회
    //nativeQuery = true 하면 sql구문 사용가능.
}
