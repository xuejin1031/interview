package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);

    @Procedure(name = "create_comment")
    void createComment(@Param("p_post_id") Long postId, @Param("p_user_id") Long userId, @Param("p_content") String content);

    @Procedure(name = "get_comments_by_post")
    List<Object[]> getCommentsByPost(@Param("p_post_id") Long postId);
}