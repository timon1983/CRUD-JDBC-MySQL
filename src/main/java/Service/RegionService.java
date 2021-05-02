package Service;

import Model.Region;
import Repository.GenericReposiroty;
import Repository.jdbc.RegionRepositoryImpl;
import View.RegionView;

import java.sql.SQLException;
import java.util.List;

public class RegionService {

    private GenericReposiroty regionRepository;

    public RegionService(RegionRepositoryImpl regionRepository){
        this.regionRepository = regionRepository;
    }
    public Region checkSaveService(String name) throws SQLException, ClassNotFoundException {
        Region region = new Region(0,name);
        return (Region) regionRepository.save(region);
    }

    public Region checkGetByIdService(Long id) throws SQLException, ClassNotFoundException {
        Region chekSuchRegion = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null);// Cheking that id is valid
        if (chekSuchRegion != null) {
            return (Region) regionRepository.getById(id);
        }else {
            return null;
        }
    }

    public List<Region> checkGetAllService() throws SQLException, ClassNotFoundException {
        List<Region> regions = regionRepository.getAll();
        if (regions != null) {
            return regions;
        } else {
            return  null;
        }
    }


    public Region checkUpdateService(Long id, String name) throws SQLException, ClassNotFoundException {
        Region chekSuchRegion = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null); // Cheking that id is valid
        if (chekSuchRegion != null) {
            return (Region) regionRepository.update(new Region(id, name));
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id) throws SQLException, ClassNotFoundException {
        RegionView view = new RegionView();
        Region chekSuchPost = checkGetAllService().stream().filter(x -> (x.getId() == id)).findAny().orElse(null);// Cheking that id is valid
        if (chekSuchPost != null) {
            regionRepository.deleteById(id);
        } else {
            view.printNoSuchElement();
        }
    }
}
