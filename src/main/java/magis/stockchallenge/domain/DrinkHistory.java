package magis.stockchallenge.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "drink_history")
public class DrinkHistory {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "volume")
    private int volume;

    @Column(name = "transaction_type")
    private String transactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id", referencedColumnName = "id", nullable = false)
    private Sector sector;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drink_type_id", referencedColumnName = "id", nullable = false)
    private DrinkType drinkType;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "last_update")
    private Date lastUpdate;

    @Column(name = "updated_at")
    private Timestamp updatedAt;


}
