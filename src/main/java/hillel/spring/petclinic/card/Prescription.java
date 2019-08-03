package hillel.spring.petclinic.card;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
@Embeddable
public class Prescription {
    private String medicineName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer timesPerDay;
}
