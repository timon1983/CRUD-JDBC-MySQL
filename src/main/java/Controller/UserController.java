package Controller;

import Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserController {

    User checkSaveController(String name1, String name2, String name3 ) throws SQLException, ClassNotFoundException;

    User checkGetByIdController(Long id) throws SQLException, ClassNotFoundException;

    List<User> checkGetAllController() throws SQLException, ClassNotFoundException;

    User checkUpdateController(Long id , String name1, String name2, String name3) throws SQLException, ClassNotFoundException;

    void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException;
}
