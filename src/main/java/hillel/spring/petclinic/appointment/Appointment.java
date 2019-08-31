package hillel.spring.petclinic.appointment;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
    private Integer doctorId;
    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> hours = new ArrayList<>();

    public Appointment(Integer doctorId, LocalDate date) {
        this.doctorId = doctorId;
        this.date = date;
    }
}
