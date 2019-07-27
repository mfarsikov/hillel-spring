package hillel.spring.petclinic.pet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Pet {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String breed;
    private Integer age;
    private String owner;
}
