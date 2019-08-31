package hillel.spring.petclinic.appointment;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AppointmentInputDto {
    private Integer doctorId;
    private LocalDate date;
    private Integer hour;
}
