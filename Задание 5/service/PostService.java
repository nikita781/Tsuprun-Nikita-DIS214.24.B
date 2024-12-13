// PostService.java
package org.example.service;

import org.example.entity.Post;

import java.util.Optional;

public interface PostService {
    Optional<Post> getPostById(int id); // обёртка для объекта Post, которая позволяет безопасно работать со значением

    int addPost(Post post);
}
