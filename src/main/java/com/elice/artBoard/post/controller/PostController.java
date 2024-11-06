package com.elice.artBoard.post.controller;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.entity.PostPostDto;
import com.elice.artBoard.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 목록 페이지
    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "post/list";
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/detail";  // 게시글 상세 페이지로 이동
    }

    // 게시글 생성 페이지
    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("postPostDto", new PostPostDto());

        return "post/create";
    }

    // 게시글 생성 처리
    @PostMapping
    public String createPost(@ModelAttribute("postPostDto") PostPostDto postPostDto) {
        Post post = new Post();
        post.setTitle(postPostDto.getTitle());
        post.setContent(postPostDto.getContent());

        postService.savePost(post);
        return "redirect:/posts";
    }

    // 게시글 수정 페이지
    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable Integer postId, Model model) {
        Post post = postService.getPost(postId);
        PostPostDto postPostDto = new PostPostDto(post.getTitle(), post.getContent());
        model.addAttribute("postId", postId);
        model.addAttribute("postPostDto", postPostDto);
        return "post/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/{postId}")
    public String updatePost(@PathVariable Integer postId, @ModelAttribute PostPostDto postPostDto) {
        Post post = postService.getPost(postId);
        post.setTitle(postPostDto.getTitle());
        post.setContent(postPostDto.getContent());
        postService.savePost(post);
        return "redirect:/posts";
    }

    // 특정 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }


}
