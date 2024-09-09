package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkHistory;
import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.exception.DrinkTypeMismatchException;
import magis.stockchallenge.exception.DrinkVolumeExceedException;
import magis.stockchallenge.exception.InsuficientSectorCapacityException;
import magis.stockchallenge.exception.InvalidSectorIdException;
import magis.stockchallenge.factory.DrinkHistoryFactory;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.model.response.DrinkHistorySimpleResponse;
import magis.stockchallenge.gateway.repository.DrinkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DrinkInsertServiceImpl {


    @Autowired
    private SectorServiceImpl sectorServiceImpl;

    @Autowired
    private DrinkHistoryRepository drinkHistoryRepository;

    @Autowired
    private DrinkTypeServiceImpl drinkTypeServiceImpl;

    @Autowired
    private DrinkHistoryFactory drinkHistoryFactory;


    public DrinkHistorySimpleResponse insertDrink(DrinkRequest drinkRequest) throws DrinkVolumeExceedException, InvalidSectorIdException, DrinkTypeMismatchException, InsuficientSectorCapacityException {

        DrinkHistory drink = new DrinkHistory();

        DrinkType drinkType = drinkTypeServiceImpl.validateAndRetrieveDrinkType(drinkRequest.getDrinkType());
        if (drinkRequest.getSectorId() != null) {
            Sector sector = sectorServiceImpl.getSectorById(drinkRequest.getSectorId());
            if (sector.getDrinkType() == null) {
                sector = sectorServiceImpl.updateSectorForNullDrinkType(drinkRequest, sector, drinkType);
            } else {
                drinkTypeMatch(drinkType, sector.getDrinkType());
                sectorHasEnoughSpace(drinkRequest, sector);
                sectorServiceImpl.updateVolumeAndSaveSector(sector, drinkRequest.getVolume());
            }
            drink = drinkHistoryFactory.createDrink(drinkRequest, sector, drinkType);

        } else {
            throw new InvalidSectorIdException("Insert a valid sectorId");
        }
        DrinkHistory savedDrinkHistory = drinkHistoryRepository.save(drink);
        return drinkHistoryFactory.getDrinkHistorySimpleResponse(savedDrinkHistory);
    }

    private void sectorHasEnoughSpace(DrinkRequest drinkRequest, Sector sector) {
        if (sector.getAvailableVolume() < drinkRequest.getVolume()) {
            throw new InsuficientSectorCapacityException();
        }
    }

    private void drinkTypeMatch(DrinkType drinkType, DrinkType sectorDrinkType) throws DrinkTypeMismatchException {
        if (!Objects.equals(drinkType.getId(), sectorDrinkType.getId())) {
            throw new DrinkTypeMismatchException();
        }
    }
}
