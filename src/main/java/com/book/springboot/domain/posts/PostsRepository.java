package com.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//보통 Dao라고 불리는 DB Layer 접근자
//Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
    List<Posts> findAllDesc();
}
