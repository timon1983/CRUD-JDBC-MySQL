package controller;

import model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.PostService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class PostControllerTest {

    @Mock
    private PostController postController = Mockito.mock(PostController.class);
    @Mock
    private PostService postService = Mockito.mock(PostService.class);

    @Test
    void checkSaveController_Should_Return_Post() {
        Post post = new Post(0, "content", 2L, 3L);
        when(postService.checkSaveService("content", 2L, 3L)).thenReturn(post);
    }

    @Test
    void checkGetByIdController_Should_Return_Post_By_Id() {
        Post post = new Post(2L, "content", 2L, 3L);
        when(postService.checkGetByIdService(2L)).thenReturn(post);
    }

    @Test
    void checkGetAllController_Should_Show_All_of_Posts() {
        List<Post> posts = new ArrayList<>();
        when(postService.checkGetAllService()).thenReturn(posts);
    }

    @Test
    void checkUpdateController_Should_Return_UpdatePost() {
        Post post = new Post(2L, "content", 2, 3);
        when(postService.checkUpdateService(2L, "content", 2L, 3L)).thenReturn(post);
    }

    @Test
    void checkDeleteByIdController() {
        postController.checkDeleteByIdController(2L);
        Mockito.verify(postController).checkDeleteByIdController(2L);
    }
}