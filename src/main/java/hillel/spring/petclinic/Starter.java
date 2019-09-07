package hillel.spring.petclinic;

import hillel.spring.petclinic.pet.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class Starter implements ApplicationRunner {

    private final PetRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Application has started, DB contains " + repository.count() + " pets");
    }
}
