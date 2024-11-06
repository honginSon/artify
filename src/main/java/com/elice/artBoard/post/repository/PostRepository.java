package com.elice.artBoard.post.repository;

import com.elice.artBoard.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

/*  JPA를 사용하면 기본적으로 CRUD를 사용한다는데 너무 코드가 없어서 당황스러움
    List<Post> findAll(); // 모든 게시글 조회

    Optional<Post> findById(Integer post_id);

    Post save(Post post);

    void deleteById(Integer post_id);*/

}
