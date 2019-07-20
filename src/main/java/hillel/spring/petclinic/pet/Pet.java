package hillel.spring.petclinic.pet;

import lombok.Data;

@Data
public class Pet {
    private Integer id;
    private String name;
    private String breed;
    private Integer age;
    private Owner owner;
}
