package com.elice.artBoard.comment.repository;


import com.elice.artBoard.comment.domain.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceContext
    EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public Comment findById(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public List<Comment> findAll(Long commentId) {
        return em.createQuery("select c from Comment c " +
                        "join fetch c.post " +
                        "join fetch c.member " +
                        "where c.id = :commentId", Comment.class)
                .setParameter("commentId", commentId)
                .getResultList();
    }

    public void delete(Comment comment) {
        em.remove(comment);
    }
}
