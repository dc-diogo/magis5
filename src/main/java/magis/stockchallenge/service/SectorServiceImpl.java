package magis.stockchallenge.service;

import jakarta.validation.constraints.Min;
import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.exception.InvalidSectorIdException;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.model.request.SectorRequest;
import magis.stockchallenge.gateway.repository.DrinkTypeRepository;
import magis.stockchallenge.gateway.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SectorServiceImpl {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private DrinkTypeRepository drinkTypeRepository;

    public Sector create(SectorRequest sectorRequest) {
        List<DrinkType> all = drinkTypeRepository.findAll();
        Sector sector = buildSectorFromRequest(sectorRequest, all.get(0));
        return sectorRepository.save(sector);
    }

    private Sector buildSectorFromRequest(SectorRequest sectorRequest, DrinkType drinkType) {
         return new Sector().builder()
                .name(sectorRequest.getName())
                .drinkType(drinkType)
                .lastUpdate(new Date())
                .availableVolume(500)
                .build();

    }

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public Sector getSectorById(int sectorId) throws InvalidSectorIdException {
            Sector sectorById = sectorRepository.findSectorById(sectorId);
            if (sectorById == null) {
                throw new InvalidSectorIdException();
            }
            return sectorById;

    }

    public void saveOrUpdateSector(Sector sector) {
        try {
            sectorRepository.save(sector);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSectorEmpty(Sector sector) {
        return (sector.getAvailableVolume() == sector.getDrinkType().getVolume());
    }

    public Sector updateSectorForNullDrinkType(DrinkRequest drinkRequest, Sector sector, DrinkType drinkType) {
        sector.setDrinkType(drinkType);
        sector.setLastUpdate(new java.util.Date());
        sector.setAvailableVolume(drinkType.getVolume() - drinkRequest.getVolume());
        return sector;
    }

    public void updateVolumeAndSaveSector(Sector sector, int volume) {
        sector.setLastUpdate(new java.util.Date());
        sector.setAvailableVolume(sector.getAvailableVolume() - volume);
        saveOrUpdateSector(sector);
    }
}

