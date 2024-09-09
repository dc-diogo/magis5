package magis.stockchallenge.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkHistorySimpleResponse {

    private int volume;
    private String drinkTypeName;
    private Long sectorId;
    private String updateBy;
}
