package service;

import model.Region;
import repository.GenericReposiroty;
import repository.jdbc.RegionRepositoryImpl;
import view.RegionView;
import java.util.List;

public class RegionService {

    private RegionRepositoryImpl regionRepository = new RegionRepositoryImpl();

    public Region checkSaveService(String name){
        Region region = new Region(0,name);
        return regionRepository.save(region);
    }

    public Region checkGetByIdService(Long id) {
        Region region = regionRepository.getById(id);
        if (region != null) {
            return region;
        }else {
            return null;
        }
    }

    public List<Region> checkGetAllService() {
        List<Region> regions = regionRepository.getAll();
        if (regions != null) {
            return regions;
        } else {
            return  null;
        }
    }


    public Region checkUpdateService(Long id, String name) {
        Region region = new Region(id, name);
        Region regionUpdate = regionRepository.update(region);
        if (regionUpdate != null) {
            return regionUpdate;
        }else{
            return null;
        }
    }

    public void checkDeleteByIdService(Long id) {
        RegionView view = new RegionView();
        Region region = regionRepository.getById(id);
         regionRepository.deleteById(id);
        if (region == null) {
            view.printNoSuchElement();
        }
    }
}
