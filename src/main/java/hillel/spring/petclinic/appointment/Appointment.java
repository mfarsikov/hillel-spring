package hillel.spring.petclinic.appointment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import java.time.LocalDate;

import lombok.Data;

@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer doctorId;
    private LocalDate date;
    private Integer hour;
}
