package hillel.spring.petclinic.appointment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.hibernate.StaleObjectStateException;
import org.springframework.retry.annotation.Retryable;
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
    @Retryable(StaleObjectStateException.class)
    public void create(@RequestBody AppointmentInputDto dto) {

        val appointment = repository.findByDoctorIdAndDate(dto.getDoctorId(),
                                                           dto.getDate())
                .orElse(new Appointment(dto.getDoctorId(), dto.getDate()));

        Thread.sleep(3000);

        if(appointment.getHours().contains(dto.getHour())) throw new RuntimeException();

        appointment.getHours().add(dto.getHour());

        repository.save(appointment);
    }
}
