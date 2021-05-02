package Controller;

import Model.Region;

import java.sql.SQLException;
import java.util.List;

public interface RegionController {

    Region checkSaveController(String name) throws SQLException, ClassNotFoundException;

    Region checkGetByIdController(Long id) throws SQLException, ClassNotFoundException;

    List<Region> checkGetAllController() throws SQLException, ClassNotFoundException;

    Region checkUpdateController(Long id, String name) throws SQLException, ClassNotFoundException;

    void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException;
}
