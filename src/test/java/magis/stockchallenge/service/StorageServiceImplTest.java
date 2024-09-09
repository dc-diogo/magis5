package magis.stockchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.gateway.model.response.SectorAvailableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StorageServiceImplTest {

    @Autowired
    StorageServiceImpl storageService;

    @MockBean
    SectorServiceImpl sectorService;

    @MockBean
    DrinkTypeServiceImpl drinkTypeService;


    @Test
    void checkStorageForVolumeAndDrinkType_noSuitableDrinkType_returnEmptyList() {
        when(drinkTypeService.validateAndRetrieveDrinkType(any(String.class))).thenReturn(null);

        List<SectorAvailableResponse> sectorAvailableResponses = storageService.checkStorageForVolumeAndDrinkType("NonExistingDrink", 10);
        assertEquals(0, sectorAvailableResponses.size());
    }


}
