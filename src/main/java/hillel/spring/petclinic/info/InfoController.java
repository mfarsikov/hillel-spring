package hillel.spring.petclinic.info;

import java.time.Clock;
import java.time.LocalDateTime;

import hillel.spring.petclinic.WorkingHoursConfig;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    private final String clinicName;
    private final WorkingHoursConfig workingHoursConfig;
    private final Clock clock;

    public InfoController(@Value("${pet-clinic.name}") String clinicName,
                          WorkingHoursConfig workingHoursConfig,
                          Clock clock) {

        this.clinicName = clinicName;
        this.workingHoursConfig = workingHoursConfig;
        this.clock = clock;
    }

    @GetMapping("/clinic-name")
    public String getName() {
        return clinicName;
    }

    @GetMapping("/working-time")
    public String getWorkingTime() {
        return workingHoursConfig.getStart() + " - " + workingHoursConfig.getEnd();
    }

    @GetMapping("/time")
    public LocalDateTime time() {
        return LocalDateTime.now(clock);
    }

}
