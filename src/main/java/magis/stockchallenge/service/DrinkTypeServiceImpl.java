package magis.stockchallenge.service;

import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.exception.DrinkTypeInvalidException;
import magis.stockchallenge.gateway.repository.DrinkTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkTypeServiceImpl {

    @Autowired
    DrinkTypeRepository drinkTypeRepository;

    public DrinkType validateAndRetrieveDrinkType(String drinkType) throws DrinkTypeInvalidException {
        List<DrinkType> foundDrinkType = drinkTypeRepository.findByName(drinkType);
        if (foundDrinkType == null || foundDrinkType.isEmpty()) {
            throw new DrinkTypeInvalidException("Drink type " + drinkType + " is invalid.");
        }
        return foundDrinkType.get(0);
    }

}
