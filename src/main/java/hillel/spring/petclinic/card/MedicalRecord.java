package hillel.spring.petclinic.card;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;
import java.util.List;

import hillel.spring.petclinic.ListToStringConverter;
import lombok.Data;

@Data
@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @Convert(converter = ListToStringConverter.class)
    private List<String> complaints;
    @Embedded
    private Prescription prescription;
}
