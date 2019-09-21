package hillel.spring.petclinic.diagnosis;

import java.util.List;

import lombok.Data;

@Data
public class Symptoms {
    private final List<String> symptoms;
}
