package hillel.spring.petclinic.card;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
@Entity
public class MedicalCard {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer petId;
    private LocalDateTime createdDate;
    @OneToOne(cascade = CascadeType.ALL)
    private SpecialNote specialNote;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MedicalRecord> records;
}