package Controller.controllers;

import Controller.UserController;
import Model.User;
import Repository.jdbc.UserRepositoryImpl;
import Service.UserService;
import java.sql.SQLException;
import java.util.List;


public class UserControllerImpl implements UserController {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();
    UserService userService = new UserService(userRepository);

    @Override
    public User checkSaveController(String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {
        return userService.checkSaveService(firstName,lastName,regionName);
    }

    @Override
    public User checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return userService.checkGetByIdService(id);
    }

    @Override
    public List<User> checkGetAllController() throws SQLException, ClassNotFoundException {
        return userService.checkGetAllService();
    }

    @Override
    public User checkUpdateController(Long id,String firstName, String lastName, String regionName) throws SQLException, ClassNotFoundException {
        return userService.checkUpdateService(id,firstName,lastName,regionName);
    }

    @Override
    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        userService.checkDeleteByIdService(id);
    }
}
