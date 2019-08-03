package hillel.spring.petclinic.card;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime date;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> complaints;
    @Embedded
    private Prescription prescription;
}
