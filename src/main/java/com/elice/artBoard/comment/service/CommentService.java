package com.elice.artBoard.comment.service;

import com.elice.artBoard.comment.domain.Comment;
import com.elice.artBoard.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId);
        comment.update(content);
        return comment;
    }

    public void removeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        commentRepository.delete(comment);
    }

    public List<Comment> findComments(Long commentId) {
        return commentRepository.findAll(commentId);
    }
}
