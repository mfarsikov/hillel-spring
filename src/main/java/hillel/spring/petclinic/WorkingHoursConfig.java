package hillel.spring.petclinic;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Configuration
@ConfigurationProperties("pet-clinic.working-hours")
@Validated
public class WorkingHoursConfig {
    @NotBlank
    private String start;
    @NotBlank
    private String end;
}
