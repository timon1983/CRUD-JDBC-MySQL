package Service;

import Model.Post;
import Repository.GenericReposiroty;
import Repository.jdbc.PostRepositoryImpl;
import View.PostView;

import java.sql.SQLException;
import java.util.List;

public class PostService {

    private GenericReposiroty postRepository;


    public PostService(PostRepositoryImpl postRepository){
        this.postRepository = postRepository;
    }

    public Post checkSaveService(String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        Post post = new Post(0L, content, created, updated );
        return (Post) postRepository.save(post);
    }

    public Post checkGetByIdService(Long id) throws SQLException, ClassNotFoundException {
        Post chekSuchPost = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null);// Cheking that id is valid
        if (chekSuchPost != null) {
            return (Post) postRepository.getById(id);
        }else {
            return null;
        }
    }

    public List<Post> checkGetAllService() throws SQLException, ClassNotFoundException {
        List<Post> posts = postRepository.getAll();
        if (posts != null) {
            return posts;
        } else {
            return  null;
        }
    }


    public Post checkUpdateService(Long id, String content, Long created, Long updated) throws SQLException, ClassNotFoundException {
        Post chekSuchPost = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null); // Cheking that id is valid
        if (chekSuchPost != null) {
            return (Post) postRepository.update(new Post(id, content, created, updated));
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id) throws SQLException, ClassNotFoundException {
        PostView view = new PostView();
        Post chekSuchPost = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null);// Cheking that id is valid
        if (chekSuchPost != null) {
            postRepository.deleteById(id);
        } else {
            view.printNoSuchElement();
        }
    }
}
