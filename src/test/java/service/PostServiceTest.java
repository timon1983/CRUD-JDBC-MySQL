package service;

import model.Post;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.jdbc.PostRepositoryImpl;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class PostServiceTest {

    @Mock
    PostRepositoryImpl postRepository = Mockito.mock(PostRepositoryImpl.class);
    @Mock
    PostService postServiceMock = Mockito.mock(PostService.class);

    @Test
    void checkSaveService_Should_Return_Post(){
        Post post = new Post(0, "content", 2L, 3L);
        when(postRepository.save(post)).thenReturn(post);
    }

    @Test
    void checkGetByIdService_Should_Return_Post_By_Id(){
        Post post = new Post(2L, "content", 2L, 3L);
        when(postRepository.getById(2L)).thenReturn(post);
    }

    @Test
    void checkGetAllService_Should_Show_All_of_Posts() {
        List<Post> posts = new ArrayList<>();
        when(postRepository.getAll()).thenReturn(posts);
    }

    @Test
    void checkUpdateService_Should_Return_UpdatePost() {
        Post post = new Post(2L, "content", 2, 3);
        when(postRepository.update(post)).thenReturn(post);
    }

    @Test
    void checkDeleteByIdService_Should_Run_DeletePost() {
        postServiceMock.checkDeleteByIdService(2L);
        Mockito.verify(postServiceMock).checkDeleteByIdService(2L);
    }
}