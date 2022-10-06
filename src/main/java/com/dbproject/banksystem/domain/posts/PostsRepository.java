package com.dbproject.banksystem.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository < Entity class, PK 타입>
// @Repository 추가할 필요 x
// Entity 클래스와 기본 Entity Repository 는 함께 위치해야 함

public interface PostsRepository extends JpaRepository<Posts, Long> {
    // SpringDataJpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 됨
    // 쿼리가 훨씬 가독성이 좋으나 SpringDataDJpa 제공 기본 메소드만으로도 해결 가능
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}