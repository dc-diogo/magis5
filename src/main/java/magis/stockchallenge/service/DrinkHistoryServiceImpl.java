package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkHistory;
import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.exception.*;
import magis.stockchallenge.factory.DrinkFactory;
import magis.stockchallenge.factory.DrinkHistoryFactory;
import magis.stockchallenge.gateway.model.response.DrinkHistoryResponse;
import magis.stockchallenge.gateway.model.response.DrinkHistorySimpleResponse;
import magis.stockchallenge.gateway.model.response.DrinkTypeTotalVolumeResponse;
import magis.stockchallenge.gateway.repository.DrinkHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DrinkHistoryServiceImpl {

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


    public List<DrinkHistorySimpleResponse> getDrinksByCriteria(Integer volume, Integer sectorId, String drinkType) {

        if (volume == null && sectorId == null && drinkType == null) {
            throw new IllegalArgumentException("At least one search criteria must be provided");
        }

        List<DrinkHistory> drinks = drinkHistoryRepository.findAll();

        return drinks.stream()
                .filter(drink -> isVolumeQueried(volume, drink) &&
                        isSectorQueried(sectorId, drink) &&
                        isDrinkTypeQueried(drinkType, drink))
                .map(drink -> drinkHistoryFactory.getDrinkHistorySimpleResponse(drink)
                )
                .collect(Collectors.toList());
    }

    public List<DrinkHistoryResponse> getDrinkUpdateHistory(String drinkTypeName, Integer sectorId) {

        DrinkType drinkType = drinkTypeServiceImpl.validateAndRetrieveDrinkType(drinkTypeName);
        Sector sector = sectorServiceImpl.getSectorById(sectorId);

        if (!Objects.equals(drinkType.getId(), sector.getDrinkType().getId())) {
            throw new DrinkTypeMismatchException("The drink type does not match the sector drink type.");
        }

        return getDrinkHistoryResponse(drinkTypeName, sectorId);
    }

    public DrinkTypeTotalVolumeResponse getTotalVolume(String drinkTypeName) {
        List<DrinkHistorySimpleResponse> drinks = getDrinksByCriteria(null, null, drinkTypeName);
        int totalOccupiedVolume = getTotalOccupiedVolume(drinks);
        return new DrinkTypeTotalVolumeResponse(drinkTypeName, totalOccupiedVolume);
    }

    private List<DrinkHistoryResponse> getDrinkHistoryResponse(String drinkTypeName, Integer sectorId) {
        return drinkHistoryRepository.findAll().stream()
                .filter(history -> isSectorQueried(sectorId, history) && isDrinkTypeQueried(drinkTypeName, history))
                .map(drinkHistory -> drinkHistoryFactory.getDrinkHistoryResponse(drinkHistory))
                .collect(Collectors.toList());
    }

    private static int getTotalOccupiedVolume(List<DrinkHistorySimpleResponse> drinks) {
        int totalOccupiedVolume = 0;
        for (DrinkHistorySimpleResponse drink : drinks) {
            totalOccupiedVolume += drink.getVolume();
        }
        return totalOccupiedVolume;
    }

    private static boolean isDrinkTypeQueried(String drinkTypeName, DrinkHistory history) {
        return drinkTypeName == null || history.getDrinkType().getName().equalsIgnoreCase(drinkTypeName);
    }

    private static boolean isSectorQueried(Integer sectorId, DrinkHistory history) {
        return sectorId == null || history.getSector().getId().equals(sectorId);
    }

    private static boolean isVolumeQueried(Integer volume, DrinkHistory drink) {
        return volume == null || drink.getVolume() == volume;
    }
}
