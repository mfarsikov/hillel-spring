package hillel.spring.petclinic.card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SpecialNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Integer id;
    private Boolean allergic;
    private String note;
}
