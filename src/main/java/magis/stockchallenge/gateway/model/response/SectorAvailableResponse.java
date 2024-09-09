package magis.stockchallenge.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectorAvailableResponse {

    private int id;
    private String name;
    private int availableVolume;

}
