package hillel.spring.petclinic.pet;

import lombok.Data;

@Data
public class Pet {
    private final Integer id;
    private final String name;
    private final String breed;
    private final Integer age;
    private final Owner owner;
}
