package Controller.controllers;

import Controller.PostController;
import Model.Post;
import Repository.jdbc.PostRepositoryImpl;
import Service.PostService;

import java.sql.SQLException;
import java.util.List;

public class PostControllerImpl implements PostController {

    private PostRepositoryImpl postRepository = new PostRepositoryImpl();
    private PostService postService = new PostService(postRepository);

    @Override
    public Post checkSaveController(String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        return postService.checkSaveService(content,created,updated);
    }

    @Override
    public Post checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return postService.checkGetByIdService(id);
    }

    @Override
    public List<Post> checkGetAllController() throws SQLException, ClassNotFoundException {
        return postService.checkGetAllService();
    }

    @Override
    public Post checkUpdateController(Long id, String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        return postService.checkUpdateService(id,content,created,updated);
    }

    @Override
    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        postService.checkDeleteByIdService(id);
    }
}
