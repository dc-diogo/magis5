package magis.stockchallenge.gateway.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrinkRequest {

    @NotNull(message = "'operatorName' in mandatory")
    private String operatorName;
    @Min(value = 0, message = "Volume must be a positive number")
    private int volume;
    private String drinkType;
    private Integer sectorId;

}
