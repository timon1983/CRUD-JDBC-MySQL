package controller;

import model.Region;
import repository.jdbc.RegionRepositoryImpl;
import service.RegionService;

import java.sql.SQLException;
import java.util.List;

public class RegionController {

    private RegionService regionService = new RegionService();

    public Region checkSaveController(String name){
        return regionService.checkSaveService(name);
    }

    public Region checkGetByIdController(Long id){
        return regionService.checkGetByIdService(id);
    }

    public List<Region> checkGetAllController(){
       return regionService.checkGetAllService();
    }

    public Region checkUpdateController(Long id, String name){
        return regionService.checkUpdateService(id, name);
    }

    public void checkDeleteByIdController(Long id){
        regionService.checkDeleteByIdService(id);
    }
}
