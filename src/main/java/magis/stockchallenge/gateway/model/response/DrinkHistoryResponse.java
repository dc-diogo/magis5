package magis.stockchallenge.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkHistoryResponse {

    private Long id;
    private int volume;
    private String transactionType;
    private Long sectorId;
    private String updateBy;
    private Date updatedAt;
    private String drinkTypeName;

}
