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
    public void createPost(Long userId, String content) {
        postRepository.createPost(userId, content);
    }

    public List<Object[]> getAllPosts() {
        return postRepository.getAllPosts();
    }

    @Transactional
    public void updatePost(Long id, String content) {
        postRepository.updatePost(id, content);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deletePost(id);
    }
}