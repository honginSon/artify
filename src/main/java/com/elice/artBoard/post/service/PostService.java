package com.elice.artBoard.post.service;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.entity.PostPostDto;
import com.elice.artBoard.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
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
    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // 게시글 저장
    /*public Post savePost(Post post) {
        return postRepository.save(post);
    }*/
    public Post save(PostPostDto postPostDto) {
        Post post = Post.create(postPostDto.getTitle(), postPostDto.getContent());
        return postRepository.save(post);
    }

    public Post update(Long postId, PostPostDto postPostDto) {
        Post findPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return findPost.update(postPostDto.getTitle(), postPostDto.getContent());
    }

    // 특정 게시글 삭제
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }
}
