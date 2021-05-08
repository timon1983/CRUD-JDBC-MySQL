package service;

import model.Post;
import repository.GenericReposiroty;
import repository.jdbc.PostRepositoryImpl;
import view.PostView;
import java.util.List;

public class PostService {

    private GenericReposiroty postRepository;


    public PostService(PostRepositoryImpl postRepository){
        this.postRepository = postRepository;
    }

    public Post checkSaveService(String content, Long created, Long updated) {
        Post post = new Post(0L, content, created, updated );
        return (Post) postRepository.save(post);
    }

    public Post checkGetByIdService(Long id) {
        Post post = (Post) postRepository.getById(id);
        if (post != null) {
            return post;
        }else {
            return null;
        }
    }

    public List<Post> checkGetAllService() {
        List<Post> posts = postRepository.getAll();
        if (posts != null) {
            return posts;
        } else {
            return  null;
        }
    }


    public Post checkUpdateService(Long id, String content, Long created, Long updated) {
        Post post = new Post(id, content, created, updated);
        Post postUpdate = (Post) postRepository.update(post);
        if (postUpdate != null) {
            return postUpdate;
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id) {
        PostView view = new PostView();
        Post post = (Post) postRepository.getById(id);
        postRepository.deleteById(id);
        if (post == null) {
            view.printNoSuchElement();
        }
    }
}
