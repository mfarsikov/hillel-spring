package hillel.spring.petclinic.pet.dto;

import java.util.Optional;

import hillel.spring.petclinic.pet.Owner;
import lombok.Data;

@Data
public class PetInputDto {
    private String name;
    private String breed;
    private Integer age;
    private String owner;

    public Optional<String> getOwner() {
        return Optional.ofNullable(owner);
    }
}
