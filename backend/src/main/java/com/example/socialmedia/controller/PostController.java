package com.example.socialmedia.controller;

import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createPost(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        String content = (String) request.get("content");
        String image = (String) request.get("image");
        postService.createPost(userId, content, image);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Object[]>> getAllPosts() {
        List<Object[]> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Map<String, String>> updatePost(@PathVariable Long postId, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        String image = request.get("image");
        postService.updatePost(postId, content, image);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post deleted successfully");
        return ResponseEntity.ok(response);
    }
}