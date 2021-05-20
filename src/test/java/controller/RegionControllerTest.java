package controller;

import model.Region;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.RegionService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class RegionControllerTest {

    @Mock
    private RegionService regionService = Mockito.mock(RegionService.class);
    @Mock
    private RegionController regionController = Mockito.mock(RegionController.class);

    @Test
    void checkSaveController_Should_Return_Region() {
        Region region = new Region(0L, "USA");
        when(regionService.checkSaveService("USA")).thenReturn(region);
    }

    @Test
    void checkGetByIdController_Should_Return_Region_By_Id() {
        Region region = new Region(2L, "USA");
        when(regionService.checkGetByIdService(2L)).thenReturn(region);
    }

    @Test
    void checkGetAllController_Should_Show_All_of_Regions() {
        List<Region> regions = new ArrayList<>();
        when(regionService.checkGetAllService()).thenReturn(regions);
    }

    @Test
    void checkUpdateController_Should_Return_UpdateRegion() {
        Region region = new Region(2L, "USA");
        when(regionService.checkUpdateService(2L, "USA")).thenReturn(region);
    }

    @Test
    void checkDeleteByIdController() {
        regionController.checkDeleteByIdController(2L);
        Mockito.verify(regionController).checkDeleteByIdController(2L);
    }
}