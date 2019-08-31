package hillel.spring.petclinic.appointment;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    Optional<Appointment> findByDoctorIdAndDate(Integer doctorId, LocalDate date);
}
