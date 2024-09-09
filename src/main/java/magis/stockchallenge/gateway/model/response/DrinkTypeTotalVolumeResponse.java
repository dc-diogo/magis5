package magis.stockchallenge.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkTypeTotalVolumeResponse {

    private String drinkTypeName;
    private int totalVolume;
}
