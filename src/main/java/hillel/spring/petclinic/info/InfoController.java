package hillel.spring.petclinic.info;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import hillel.spring.petclinic.WorkingHoursConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
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

        try {
            log.info("Calling doctor service");

            log.debug("Doctor service URL: {}", doctorServiceConfig.getDoctorUrl());
            log.trace("now is {}", LocalDateTime.now(clock));
            val staff = restTemplate.getForObject(doctorServiceConfig.getDoctorUrl() + "/doctors", Staff.class);
            log.info("Doctor service responded ok");
            log.debug("Doctor  service response: {}", staff);
            return staff;
        } catch (Exception e) {
            log.error("Call  to doctor service finished  exceptionally", e);
        }
        return null;
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