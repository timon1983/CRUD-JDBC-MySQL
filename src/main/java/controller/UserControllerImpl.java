package controller;

import model.User;
import repository.jdbc.UserRepositoryImpl;
import service.UserService;
import java.sql.SQLException;
import java.util.List;


public class UserControllerImpl  {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    UserService userService = new UserService(userRepository);

    public User checkSaveController(String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {
        return userService.checkSaveService(firstName,lastName,regionName);
    }

    public User checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return userService.checkGetByIdService(id);
    }

    public List<User> checkGetAllController() throws SQLException, ClassNotFoundException {
        return userService.checkGetAllService();
    }

    public User checkUpdateController(Long id,String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {
        return userService.checkUpdateService(id,firstName,lastName,regionName);
    }

    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        userService.checkDeleteByIdService(id);
    }
}
