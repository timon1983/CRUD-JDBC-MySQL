package Controller.controllers;

import Controller.RegionController;
import Model.Region;
import Repository.jdbc.RegionRepositoryImpl;
import Service.RegionService;

import java.sql.SQLException;
import java.util.List;

public class RegionControllerImpl implements RegionController {

    private RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();
    private RegionService regionService = new RegionService(regionRepository);


    @Override
    public Region checkSaveController(String name) throws SQLException, ClassNotFoundException {
        return regionService.checkSaveService(name);
    }

    @Override
    public Region checkGetByIdController(Long id) throws SQLException, ClassNotFoundException {
        return regionService.checkGetByIdService(id);
    }

    @Override
    public List<Region> checkGetAllController() throws SQLException, ClassNotFoundException {
       return regionService.checkGetAllService();
    }

    @Override
    public Region checkUpdateController(Long id, String name) throws SQLException, ClassNotFoundException {
        return regionService.checkUpdateService(id, name);
    }

    @Override
    public void checkDeleteByIdController(Long id) throws SQLException, ClassNotFoundException {
        regionService.checkDeleteByIdService(id);
    }
}
