package controller;

import model.Region;
import repository.jdbc.RegionRepositoryImpl;
import service.RegionService;

import java.sql.SQLException;
import java.util.List;

public class RegionControllerImpl {

    private RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
    private RegionService regionService = new RegionService(regionRepository);


    public Region checkSaveController(String name) throws SQLException, ClassNotFoundException {
        return regionService.checkSaveService(name);
    }

    public Region checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return regionService.checkGetByIdService(id);
    }

    public List<Region> checkGetAllController() throws SQLException, ClassNotFoundException {
       return regionService.checkGetAllService();
    }

    public Region checkUpdateController(Long id, String name) throws SQLException, ClassNotFoundException {
        return regionService.checkUpdateService(id, name);
    }

    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        regionService.checkDeleteByIdService(id);
    }
}
