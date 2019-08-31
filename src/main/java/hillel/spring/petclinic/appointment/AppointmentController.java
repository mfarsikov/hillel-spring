package hillel.spring.petclinic.appointment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentRepository repository;

    @GetMapping
    public List<Appointment> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @SneakyThrows
    public void create(@RequestBody Appointment appointment) {

        val conflictedAppointment = repository.findByDoctorIdAndDateAndHour(appointment.getDoctorId(),
                                                                            appointment.getDate(),
                                                                            appointment.getHour());

        Thread.sleep(3000);

        if (conflictedAppointment.isPresent()) {
            throw new RuntimeException();
        }

        repository.save(appointment);
    }
}
