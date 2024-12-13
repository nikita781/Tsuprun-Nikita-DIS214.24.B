package org.example.service;

import org.example.entity.Post;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final PostService postService;

    public UserService(PostService postService) {
        this.postService = postService;
    }

    public List<Post> getUserPosts(User user) {
        List<Post> posts = new ArrayList<>();
        for (Integer postId : user.getListPost()) {
            Optional<Post> post = postService.getPostById(postId);
            post.ifPresent(posts::add); // Если не пустой
        }
        return posts;
    }

    public void addPostToUser(User user, Post post) {
        int postId = postService.addPost(post);
        user.getListPost().add(postId);
    }

    public void removePostFromUser(User user, int postId) {
        user.getListPost().remove((Integer) postId);
    }
}