package com.elice.artBoard.post.controller;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.entity.PostImage;
import com.elice.artBoard.post.entity.PostPostDto;
import com.elice.artBoard.post.entity.PostResponseDto;
import com.elice.artBoard.post.service.PostService;
import com.elice.artBoard.post.service.PostImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static com.elice.artBoard.post.constants.DefaultImgConst.DEFAULT_IMG_PATH;

@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostImageService postImageService;

    @Autowired
    public PostController(PostService postService, PostImageService postImageService) {
        this.postService = postService;
        this.postImageService = postImageService;
    }

    // 게시글 목록 페이지
    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        List<PostImage> images = postImageService.findImagesByPostId(posts);

        List<PostResponseDto> responseDtoList = getPostResponseDtoList(posts, images);

        model.addAttribute("posts", responseDtoList);

        return "post/list";
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
//        Post post = postService.getPost(postId);
//        model.addAttribute("post", post);

        Post post = postService.getPost(postId);
        PostImage postImage = postImageService.findByPostId(postId);  // 게시글에 연결된 이미지 찾기
        model.addAttribute("post", post);
        model.addAttribute("imagePath", postImage != null ? postImage.getImagePath() : DEFAULT_IMG_PATH);  // 이미지 경로 추가
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
    public String createPost(@Validated @ModelAttribute("postPostDto") PostPostDto postPostDto) {
        // 유효성 검사 실패 시, 폼 페이지로 돌아감
//        if (bindingResult.hasErrors()) {
//            return "post/create"; // 오류가 있으면 "post/create" 페이지로 돌아감
//        }

//        Post post = new Post();
//        post.setTitle(postPostDto.getTitle());
//        post.setContent(postPostDto.getContent());
//
//        postService.savePost(post);

        Post post = postService.save(postPostDto);
        postImageService.save(postPostDto, post);
        return "redirect:/posts";
    }

    // 게시글 수정 페이지
    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable Long postId, Model model) {
//        Post post = postService.getPost(postId);
//        PostPostDto postPostDto = new PostPostDto(post.getTitle(), post.getContent());
//        model.addAttribute("postId", postId);
//        model.addAttribute("postPostDto", postPostDto);

//        Post post = postService.getPost(postId);
//        model.addAttribute("postPostDto", new PostPostDto(post.getTitle(), post.getContent(), null));

        Post post = postService.getPost(postId);
        PostPostDto postPostDto = new PostPostDto(post.getTitle(), post.getContent(), null);
        model.addAttribute("postPostDto", postPostDto); // DTO를 모델에 추가

        return "post/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/{postId}")
    public String updatePost(@PathVariable Long postId, @Validated @ModelAttribute("postPostDto") PostPostDto postPostDto, BindingResult bindingResult) {
//        Post post = postService.getPost(postId);
//        post.setTitle(postPostDto.getTitle());
//        post.setContent(postPostDto.getContent());
//        postService.savePost(post);

        if (bindingResult.hasErrors()) {
            return "post/edit";
        }

        Post post = postService.update(postId, postPostDto);
        postImageService.update(post, postPostDto);


        return "redirect:/posts";
    }

    // 특정 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        //postService.deletePost(postId);

        postImageService.delete(postId);
        postService.deletePost(postId);
        return "redirect:/posts";
    }

    @ResponseBody
    @GetMapping("image/{imageId}")
    public ResponseEntity downloadImg(@PathVariable Long imageId) throws MalformedURLException {
        PostImage image = postImageService.findByImgId(imageId);

        return getResponse(image);
    }



    private List<PostResponseDto> getPostResponseDtoList(List<Post> posts, List<PostImage> images) {
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        int bound = posts.size();

        // 게시글과 이미지 정보를 결합하여 PostResponseDto 객체 생성
        IntStream.range(0, bound).forEach(i -> {
            Post post = posts.get(i);
            PostImage postImage = images.get(i);  // 해당 게시글에 연결된 이미지

            // PostResponseDto에 게시글 정보와 이미지 ID를 담습니다.
            responseDtoList.add(new PostResponseDto(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCreatedAt(),
                    post.getEditedAt(),
                    post.getMemberId(),
                    post.getBoardId(),
                    postImage.getId()  // 이미지 ID를 포함
            ));
        });

        return responseDtoList;
    }

    private ResponseEntity getResponse(PostImage image) throws MalformedURLException {
        Resource resource;

        if (image.getImagePath().equals(DEFAULT_IMG_PATH)) {
            resource = new ClassPathResource(DEFAULT_IMG_PATH);

        } else {
            resource = new UrlResource("file:" + image.getImagePath());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }




}
