package magis.stockchallenge.gateway.repository;

import magis.stockchallenge.domain.DrinkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkHistoryRepository extends JpaRepository<DrinkHistory, Long> {



//    List<Drink> findAllByDrinkType(DrinkType drinkType);

}