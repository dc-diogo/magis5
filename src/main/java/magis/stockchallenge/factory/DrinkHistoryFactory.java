package magis.stockchallenge.factory;

import magis.stockchallenge.domain.DrinkHistory;
import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import magis.stockchallenge.gateway.model.request.DrinkRequest;
import magis.stockchallenge.gateway.model.response.DrinkHistoryResponse;
import magis.stockchallenge.gateway.model.response.DrinkHistorySimpleResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class DrinkHistoryFactory {

    public DrinkHistorySimpleResponse getDrinkHistorySimpleResponse(DrinkHistory drink) {
        return new DrinkHistorySimpleResponse().builder()
                .volume(drink.getVolume())
                .sectorId(drink.getSector().getId())
                .drinkTypeName(drink.getDrinkType().getName())
                .updateBy(drink.getUpdateBy())
                .build();
    }

    public DrinkHistoryResponse getDrinkHistoryResponse(DrinkHistory drink) {
        return new DrinkHistoryResponse().builder()
                .volume(drink.getVolume())
                .sectorId(drink.getSector().getId())
                .drinkTypeName(drink.getDrinkType().getName())
                .transactionType(drink.getTransactionType())
                .updateBy(drink.getUpdateBy())
                .updatedAt(drink.getUpdatedAt())
                .build();
    }

    public DrinkHistory createDrink(DrinkRequest drinkRequest, Sector sector,
                                    DrinkType drinkType) {
        return new DrinkHistory().builder()
                .volume(drinkRequest != null ? drinkRequest.getVolume() : 0)
                .sector(sector)
                .drinkType(drinkType != null ? drinkType : new DrinkType())
                .transactionType(drinkRequest != null && drinkRequest.getVolume() < 0 ? "OUTCOME" : "INCOME")
                .updateBy(drinkRequest.getOperatorName())
                .lastUpdate(new Date())
                .updatedAt(new Timestamp(new Date().getTime()))
                .build();
    }
}
