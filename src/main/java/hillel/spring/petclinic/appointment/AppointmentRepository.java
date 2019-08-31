package hillel.spring.petclinic.appointment;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Optional<Appointment> findByDoctorIdAndDateAndHour(Integer doctorId, LocalDate date, Integer hour);
}
