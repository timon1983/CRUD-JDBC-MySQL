package Service;

import Controller.RegionController;
import Controller.controllers.RegionControllerImpl;
import Model.Post;
import Model.Region;
import Model.User;
import Repository.GenericReposiroty;
import Repository.jdbc.PostRepositoryImpl;
import Repository.jdbc.UserRepositoryImpl;
import View.UserView;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private GenericReposiroty userRepository;
    private GenericReposiroty postRepository = new PostRepositoryImpl();
    private RegionController regionController = new RegionControllerImpl();


    public UserService(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }


    public User checkSaveService(String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {

        List<Post> posts = postRepository.getAll();
        Region region = new Region(0,regionName);
        User user = new User(0, firstName, lastName, posts, region);
        return (User) userRepository.save(user);
    }

    public User checkGetByIdService(Long id) throws SQLException, ClassNotFoundException {
        User chekSuchUser = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null); // Cheking that id is valid
        if (chekSuchUser != null) {
            return (User) userRepository.getById(id);
        }else {
            return null;
        }
    }

    public List<User> checkGetAllService() throws SQLException, ClassNotFoundException {
        List<User> users = userRepository.getAll();
        if(users != null){
            return users;
        }else {
            return null;
        }
    }

    public User checkUpdateService(Long id,String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {
        User chekSuchUser = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null); // Cheking that id is valid
        if (chekSuchUser != null) {
            List<Post> posts = postRepository.getAll();
            Region newRegion = new Region(0, regionName);
            User newUser = new User(Math.toIntExact(id), firstName, lastName, posts, newRegion);
            return (User) userRepository.update(newUser);
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id) throws SQLException, ClassNotFoundException {
        UserView view = new UserView();
        User chekSuchUser = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null); // Cheking that id is valid
        if (chekSuchUser != null) {
            userRepository.deleteById(id);
        } else {
            view.printNoSuchElement();
        }
    }
}
