package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkHistory;
import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.exception.InsuficientVolumeException;
import magis.stockchallenge.factory.DrinkFactory;
import magis.stockchallenge.factory.DrinkHistoryFactory;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.repository.DrinkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkSellServiceImpl {

    @Autowired
    private SectorServiceImpl sectorServiceImpl;

    @Autowired
    private DrinkHistoryRepository drinkHistoryRepository;

    @Autowired
    private DrinkTypeServiceImpl drinkTypeServiceImpl;

    @Autowired
    private DrinkFactory drinkFactory;

    @Autowired
    private DrinkHistoryFactory drinkHistoryFactory;

    public void sellDrink(DrinkRequest drinkRequest) {
        DrinkType drinkType = drinkTypeServiceImpl.validateAndRetrieveDrinkType(drinkRequest.getDrinkType());
        Sector sector = sectorServiceImpl.getSectorById(drinkRequest.getSectorId());
        if (drinkRequest.getVolume() > (sector.getDrinkType().getVolume() - sector.getAvailableVolume())) {
            throw new InsuficientVolumeException("Not enough volume of drink in this sector");
        }
        sector.setAvailableVolume(sector.getAvailableVolume() + drinkRequest.getVolume());

        if (sectorServiceImpl.isSectorEmpty(sector)) {
            sector.setAvailableVolume(-1);
            sector.setDrinkType(null);
        }

        sector.setLastUpdate(new java.util.Date());
        sectorServiceImpl.saveOrUpdateSector(sector);
        drinkRequest.setVolume(drinkRequest.getVolume() * -1);
        DrinkHistory drink = drinkHistoryFactory.createDrink(drinkRequest, sector, drinkType);
        drinkHistoryRepository.save(drink);
    }

}
