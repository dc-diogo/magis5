package magis.stockchallenge.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sector")
public class Sector {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "available_volume")
    private int availableVolume;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drink_type_id", referencedColumnName = "id", nullable = true)
    private DrinkType drinkType;

    @NotNull
    @Column(name = "last_update")
    private Date lastUpdate;
}