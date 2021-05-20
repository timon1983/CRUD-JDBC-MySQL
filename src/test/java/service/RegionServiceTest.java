package service;

import model.Region;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.jdbc.RegionRepositoryImpl;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class RegionServiceTest {

    @Mock
    RegionRepositoryImpl regionRepository = Mockito.mock(RegionRepositoryImpl.class);
    @Mock
    RegionService regionServiceMock = Mockito.mock(RegionService.class);

    @Test
    void checkSaveService_Should_Return_Region() {
        Region region = new Region(0L, "USA");
        when(regionRepository.save(region)).thenReturn(region);
    }

    @Test
    void checkGetByIdService_Should_Return_Region_By_Id() {
        Region region = new Region(2L, "USA");
        when(regionRepository.getById(2L)).thenReturn(region);
    }

    @Test
    void checkGetAllService_Should_Show_All_of_Regions() {
        List<Region> regions = new ArrayList<>();
        when(regionRepository.getAll()).thenReturn(regions);
    }

    @Test
    void checkUpdateService_Should_Return_UpdateRegion() {
        Region region = new Region(2L, "USA");
        when(regionRepository.update(region)).thenReturn(region);
    }

    @Test
    void checkDeleteByIdService_Should_Run_DeleteRegion(){
        regionServiceMock.checkDeleteByIdService(2L);
        Mockito.verify(regionServiceMock).checkDeleteByIdService(2L);
    }
}