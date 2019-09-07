package hillel.spring.petclinic.info;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@ConfigurationProperties("pet-clinic")
@Component
@Validated
public class DoctorServiceConfig {
    @NotBlank
    private String doctorUrl;

}
