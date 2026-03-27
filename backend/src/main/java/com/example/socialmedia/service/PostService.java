package com.example.socialmedia.service;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void createPost(Long userId, String content, String image) {
        postRepository.createPost(userId, content, image);
    }

    public List<Object[]> getAllPosts() {
        return postRepository.getAllPosts();
    }

    @Transactional
    public void updatePost(Long postId, String content, String image) {
        postRepository.updatePost(postId, content, image);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.deletePost(postId);
    }
}