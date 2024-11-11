package com.elice.artBoard.post.controller;

import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.entity.PostImage;
import com.elice.artBoard.post.entity.PostPostDto;
import com.elice.artBoard.post.service.PostService;
import com.elice.artBoard.board.service.BoardService;
import com.elice.artBoard.post.service.PostImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final PostImageService postImageService;
    private final BoardService boardService;

    // 게시글 목록 페이지
    @GetMapping
    public String getAllPosts(@RequestParam Long boardId, Model model) {
        // boardId를 파라미터로 받아옵니다.
        List<Post> posts = postService.findPostsByBoardId(boardId);  // boardId에 해당하는 게시글을 가져옵니다.
        model.addAttribute("posts", posts);
        model.addAttribute("boardId", boardId);  // boardId를 모델에 추가

        return "post/list";
    }

    // 특정 게시글 조회
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        Post post = postService.getPost(postId);
        PostImage postImage = postImageService.findImageByPostId(post);  // 게시글에 연결된 이미지 찾기

        model.addAttribute("post", post);
        if (postImage != null) {
            model.addAttribute("imageId", postImage.getId());  // 이미지 ID를 모델에 추가
        } else {
            model.addAttribute("imageId", null);  // 이미지가 없으면 null로 설정
        }

        return "post/detail";  // 게시글 상세 페이지로 이동
    }

    // 게시글 생성 페이지
    @GetMapping("/create")
    public String createPostForm(@RequestParam("boardId") Long boardId, Model model) {
        System.out.println("Received boardId: " + boardId);  // 디버깅 로그
        PostPostDto postPostDto = new PostPostDto();
        postPostDto.setBoardId(boardId);  // DTO에 boardId 설정
        model.addAttribute("postPostDto", postPostDto);
        model.addAttribute("boardId", boardId);

        return "post/create";
    }

    // 게시글 생성 처리
    @PostMapping("/create")
    public String createPost(@Validated @ModelAttribute("postPostDto") PostPostDto postPostDto,
                             @RequestParam("boardId") Long boardId) {
        Post post = postService.save(postPostDto, boardId);
        postImageService.save(postPostDto, post);
        return "redirect:/boards/board/" + boardId;
    }

    // 게시글 수정 페이지
    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable Long postId, Model model) {
        // PostPostDto 객체를 Service에서 받아옴
        PostPostDto postPostDto = postService.getPostPostDto(postId);

        // model에 담아서 뷰로 전달
        model.addAttribute("postPostDto", postPostDto);

        return "post/edit";  // 게시글 수정 폼 페이지로 이동
    }

    // 게시글 수정 처리
    @PostMapping("/{postId}")
    public String updatePost(@PathVariable Long postId, @Validated @ModelAttribute("postPostDto") PostPostDto postPostDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }

        Post post = postService.update(postId, postPostDto);
        postImageService.update(post, postPostDto);

        return "redirect:/boards/board/" + postPostDto.getBoardId();
    }

    // 특정 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        Long boardId = post.getBoard().getId();  // 게시글이 속한 게시판 ID를 가져옵니다.

        // 게시글 삭제
        postService.deletePost(postId);

        // 리디렉션 URL에서 boardId를 경로 변수로 전달
        return "redirect:/boards/board/" + boardId;
    }

    @ResponseBody
    @GetMapping("image/{imageId}")
    public ResponseEntity downloadImg(@PathVariable Long imageId) throws MalformedURLException {
        PostImage image = postImageService.findByImgId(imageId);

        return getResponse(image);
    }

    private ResponseEntity getResponse(PostImage image) throws MalformedURLException {
        Resource resource;

        resource = new UrlResource("file:" + image.getImagePath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }

}
