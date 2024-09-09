package magis.stockchallenge.gateway.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectorRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String drinkType;

}
