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
        postService.createPost(userId, content);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post created successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<Object[]>> getAllPosts() {
        List<Object[]> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updatePost(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        postService.updatePost(id, content);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post updated successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Post deleted successfully");
        return ResponseEntity.ok(response);
    }
}