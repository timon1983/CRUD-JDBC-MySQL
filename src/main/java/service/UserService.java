package service;

import model.Post;
import model.Region;
import model.User;
import repository.jdbc.PostRepositoryImpl;
import repository.jdbc.UserRepositoryImpl;
import view.UserView;
import java.util.List;

public class UserService {

    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    private PostRepositoryImpl postRepository = new PostRepositoryImpl();

    public User checkSaveService(String firstName, String lastName, String regionName) {
        List<Post> posts = postRepository.getAll();
        Region region = new Region(0,regionName);
        User user = new User(0, firstName, lastName, posts, region);
        return userRepository.save(user);
    }

    public User checkGetByIdService(Long id) {
        User user = userRepository.getById(id);
        if (user != null) {
            return user;
        }else {
            return null;
        }
    }

    public List<User> checkGetAllService(){
        List<User> users = userRepository.getAll();
        if(users != null){
            return users;
        }else {
            return null;
        }
    }

    public User checkUpdateService(Long id,String firstName, String lastName, String regionName) {
        List<Post> posts = postRepository.getAll();
        Region newRegion = new Region(0, regionName);
        User newUser = new User(Math.toIntExact(id), firstName, lastName, posts, newRegion);
        User updateUser = userRepository.update(newUser);
        if (updateUser != null) {
            return updateUser;
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id){
        UserView view = new UserView();
        User user = userRepository.getById(id);
        userRepository.deleteById(id);
        if (user == null) {
            view.printNoSuchElement();
        }
    }
}
