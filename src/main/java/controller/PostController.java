package controller;

import model.Post;
import repository.jdbc.PostRepositoryImpl;
import service.PostService;

import java.sql.SQLException;
import java.util.List;

public class PostController {

    private PostService postService = new PostService();

    public Post checkSaveController(String content, Long created, Long updated){
        return postService.checkSaveService(content,created,updated);
    }

    public Post checkGetByIdController(Long id){
        return postService.checkGetByIdService(id);
    }

    public List<Post> checkGetAllController(){
        return postService.checkGetAllService();
    }

    public Post checkUpdateController(Long id, String content, Long created, Long updated){
        return postService.checkUpdateService(id,content,created,updated);
    }

    public void checkDeleteByIdController(Long id){
        postService.checkDeleteByIdService(id);
    }
}
