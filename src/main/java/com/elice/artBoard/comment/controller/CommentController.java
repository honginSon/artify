package com.elice.artBoard.comment.controller;

import com.elice.artBoard.comment.domain.Comment;
import com.elice.artBoard.comment.dto.RequestCommentForm;
import com.elice.artBoard.comment.service.CommentService;
import com.elice.artBoard.member.entity.Member;
import com.elice.artBoard.member.service.MemberService;
import com.elice.artBoard.post.entity.Post;
import com.elice.artBoard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/create")
    public Comment createComment(RequestCommentForm form) {
        Post post = postService.getPost(form.getPostId());

        Comment comment = Comment.create(post, form.getContent(), null);
        return commentService.addComment(comment);
    }

    @PutMapping("/create/{commentId}")
    public Comment updateComment(Long commentId, RequestCommentForm form) {
        //TODO 회원 id가 같은 경우 수정
        return commentService.updateComment(commentId, form.getContent());
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(Long commentId) {
        //TODO 회원 id가 같은 경우 삭제가능
        commentService.removeComment(commentId);
    }
}
