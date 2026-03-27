package com.example.socialmedia.repository;

import com.example.socialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    @Procedure(name = "create_post")
    void createPost(@Param("p_user_id") Long userId, @Param("p_content") String content);

    @Procedure(name = "get_all_posts")
    List<Object[]> getAllPosts();

    @Procedure(name = "update_post")
    void updatePost(@Param("p_id") Long id, @Param("p_content") String content);

    @Procedure(name = "delete_post")
    void deletePost(@Param("p_id") Long id);
}