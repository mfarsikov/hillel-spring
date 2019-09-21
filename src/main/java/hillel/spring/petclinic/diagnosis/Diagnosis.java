package hillel.spring.petclinic.diagnosis;

import lombok.Data;

@Data
public class Diagnosis {
    private final String deceaseName;
    private final Boolean severe;
    private final Integer severityLevel;
}
