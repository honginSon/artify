package com.elice.artBoard.post.repository;

import com.elice.artBoard.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Query("select pi from PostImage pi join fetch pi.post p where p.id = :postId")
    Optional<PostImage> findByPostId(Long postId);

}
