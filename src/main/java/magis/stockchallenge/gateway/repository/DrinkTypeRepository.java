package magis.stockchallenge.gateway.repository;


import magis.stockchallenge.domain.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkTypeRepository extends JpaRepository<DrinkType, Long> {
    List<DrinkType> findByName(String name);

}