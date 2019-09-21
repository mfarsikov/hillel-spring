package hillel.spring.petclinic.diagnosis;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnosisController {
    @GetMapping("/diagnosis/{petId}")
    public Diagnosis getDiagnosis(@PathVariable Integer petId,
                                  @RequestParam(defaultValue = "false") Boolean truthy,
                                  @RequestBody Symptoms symptoms) {
        return new Diagnosis("Typhus", true, 3);
    }
}
