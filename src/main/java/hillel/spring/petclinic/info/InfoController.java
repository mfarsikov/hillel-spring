package hillel.spring.petclinic.info;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import hillel.spring.petclinic.WorkingHoursConfig;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class InfoController {
    private final String clinicName;
    private final WorkingHoursConfig workingHoursConfig;
    private final Clock clock;
    private final RestTemplate restTemplate;
    private final DoctorServiceConfig doctorServiceConfig;

    public InfoController(@Value("${pet-clinic.name}") String clinicName,
                          WorkingHoursConfig workingHoursConfig,
                          Clock clock,
                          DoctorServiceConfig doctorServiceConfig) {

        this.clinicName = clinicName;
        this.workingHoursConfig = workingHoursConfig;
        this.clock = clock;
        this.restTemplate = new RestTemplate();
        this.doctorServiceConfig = doctorServiceConfig;
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

    @GetMapping("/workers")
    public Staff getStaff() {
        return restTemplate.getForObject(doctorServiceConfig.getDoctorUrl() + "/doctors", Staff.class);
    }

}

@Data
class Staff {
    private final List<Doctor> doctors;
}

@Data
class Doctor {
    private final String name;
    private final String specialization;
}