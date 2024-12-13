package org.example.service;

import org.example.entity.Post;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService userService;
    private PostService postService;

    @BeforeEach
    void setUp() {
        postService = mock(PostService.class);
        userService = new UserService(postService);
    }

    @Test
    void testGetUserPosts_Success() {
        User user = new User("1", "1", "1", 25, Arrays.asList(1, 2));
        List<Post> mockPosts = Arrays.asList(
                new Post(1, "Post 1", "Description 1"),
                new Post(2, "Post 2", "Description 2")
        );

        when(postService.getPostById(1)).thenReturn(Optional.of(mockPosts.get(0)));
        when(postService.getPostById(2)).thenReturn(Optional.of(mockPosts.get(1)));

        List<Post> userPosts = userService.getUserPosts(user);

        assertEquals(2, userPosts.size());
        assertEquals("Post 1", userPosts.get(0).getTitle());
        assertEquals("Post 2", userPosts.get(1).getTitle());

        verify(postService, times(1)).getPostById(1);
        verify(postService, times(1)).getPostById(2);
    }

    @Test
    void testGetUserPosts_EmptyList() {
        User user = new User("1", "1", "1", 25, new ArrayList<>(Arrays.asList()));

        List<Post> userPosts = userService.getUserPosts(user);

        assertTrue(userPosts.isEmpty());
        verify(postService, never()).getPostById(anyInt());
    }

    @Test
    void testGetUserPosts_PostNotFound() {
        User user = new User("1", "1", "1", 25, new ArrayList<>(Arrays.asList(1)));
        when(postService.getPostById(1)).thenReturn(Optional.empty());

        List<Post> userPosts = userService.getUserPosts(user);

        assertTrue(userPosts.isEmpty());
        verify(postService, times(1)).getPostById(1);
    }

    @Test
    void testAddPostToUser() {
        User user = new User("1", "1", "1", 25, new ArrayList<>(Arrays.asList(1)));
        Post newPost = new Post(2, "Post 2", "Description 2");

        when(postService.addPost(newPost)).thenReturn(2);

        userService.addPostToUser(user, newPost);

        assertTrue(user.getListPost().contains(2));
        verify(postService, times(1)).addPost(newPost);
    }

    @Test
    void testRemovePostFromUser() {
        User user = new User("1", "1", "1", 25, new ArrayList<>(Arrays.asList(1, 2)));

        userService.removePostFromUser(user, 2);

        assertFalse(user.getListPost().contains(2));
    }
}
