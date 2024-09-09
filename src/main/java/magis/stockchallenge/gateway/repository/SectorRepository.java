package magis.stockchallenge.gateway.repository;

import magis.stockchallenge.domain.DrinkType;
import magis.stockchallenge.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

    List<Sector> findAllByDrinkType(DrinkType drinkTypeValidated);

    Sector findSectorById(int sectorId);
}