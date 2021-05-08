package controller;

import model.Post;
import repository.jdbc.PostRepositoryImpl;
import service.PostService;

import java.sql.SQLException;
import java.util.List;

public class PostControllerImpl {

    private PostRepositoryImpl postRepository = new PostRepositoryImpl();
    private PostService postService = new PostService(postRepository);


    public Post checkSaveController(String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        return postService.checkSaveService(content,created,updated);
    }


    public Post checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return postService.checkGetByIdService(id);
    }


    public List<Post> checkGetAllController() throws SQLException, ClassNotFoundException {
        return postService.checkGetAllService();
    }


    public Post checkUpdateController(Long id, String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        return postService.checkUpdateService(id,content,created,updated);
    }


    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        postService.checkDeleteByIdService(id);
    }
}
