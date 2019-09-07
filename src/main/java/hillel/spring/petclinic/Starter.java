package hillel.spring.petclinic;

import hillel.spring.petclinic.pet.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Starter implements ApplicationRunner {
    private final PetRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Application has started, DB contains " + repository.count() + " pets");
    }
}
