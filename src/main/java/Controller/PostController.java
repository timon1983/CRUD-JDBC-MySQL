package Controller;

import Model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostController {

    Post checkSaveController(String name, Long created, Long updated ) throws SQLException, ClassNotFoundException;

    Post checkGetByIdController(Long id) throws SQLException, ClassNotFoundException;

    List<Post> checkGetAllController() throws SQLException, ClassNotFoundException;

    Post checkUpdateController(Long id, String content, Long created, Long updated) throws SQLException, ClassNotFoundException;

    void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException;
}
