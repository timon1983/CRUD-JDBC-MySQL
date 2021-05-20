package controller;

import model.User;
import repository.jdbc.UserRepositoryImpl;
import service.UserService;
import java.sql.SQLException;
import java.util.List;


public class UserController {

    UserService userService = new UserService();

    public User checkSaveController(String firstName, String lastName, String regionName){
        return userService.checkSaveService(firstName,lastName,regionName);
    }

    public User checkGetByIdController(Long id){
        return userService.checkGetByIdService(id);
    }

    public List<User> checkGetAllController(){
        return userService.checkGetAllService();
    }

    public User checkUpdateController(Long id,String firstName, String lastName, String regionName){
        return userService.checkUpdateService(id,firstName,lastName,regionName);
    }

    public void checkDeleteByIdController(Long id){
        userService.checkDeleteByIdService(id);
    }
}
