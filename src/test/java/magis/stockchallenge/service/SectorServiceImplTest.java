package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.repository.DrinkTypeRepository;
import magis.stockchallenge.gateway.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class SectorServiceImplTest {

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private DrinkTypeRepository drinkTypeRepository;

    @InjectMocks
    private SectorServiceImpl sectorService;

    @Test
    public void updateSectorForNullDrinkType() {
        // Given
        DrinkRequest drinkRequest = new DrinkRequest("Diogo", 200, "Non-Alcoholic", 2);
        DrinkType drinkType = new DrinkType();
        drinkType.setId(1L);
        drinkType.setName("Water");
        drinkType.setVolume(1000);

        Sector sector = new Sector();
        sector.setId(1L);
        sector.setName("Sector 1");
        sector.setDrinkType(null);
        sector.setAvailableVolume(1200);
        sector.setLastUpdate(new java.util.Date());

        // When
        Sector updatedSector = sectorService.updateSectorForNullDrinkType(drinkRequest, sector, drinkType);

        // Then
        assertNotNull(updatedSector);
        assertNotNull(updatedSector.getDrinkType());
        assertNotNull(updatedSector.getLastUpdate());
        assertEquals(drinkType, updatedSector.getDrinkType());
        assertEquals(800, updatedSector.getAvailableVolume());
    }
}