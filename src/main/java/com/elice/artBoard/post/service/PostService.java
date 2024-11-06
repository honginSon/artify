package com.elice.artBoard.post.service;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 모든 게시글 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 게시글 조회
    public Post getPost(Integer postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 게시글 저장
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // 특정 게시글 삭제
    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }
}
