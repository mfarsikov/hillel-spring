package hillel.spring.petclinic.pet;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import hillel.spring.petclinic.pet.dto.PetDtoConverter;
import hillel.spring.petclinic.pet.dto.PetInputDto;
import lombok.val;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpStatus.*;

@RestController
public class PetController {
    private final PetService petService;
    private final PetDtoConverter dtoConverter;

    private final UriComponentsBuilder uriBuilder;

    public PetController(PetService petService,
                         PetDtoConverter dtoConverter,
                         @Value("${pet-clinic.host-name:localhost}") String hostName
    ) {
        this.petService = petService;
        this.dtoConverter = dtoConverter;
        uriBuilder = UriComponentsBuilder.newInstance()
                                         .scheme("http")
                                         .host(hostName)
                                         .path("/pets/{id}");
    }

    @GetMapping("/pets/{id}")
    public Pet findById(@PathVariable Integer id) {
        val mayBePet = petService.findById(id);

        return mayBePet.orElseThrow(PetNotFoundException::new);
    }

    @GetMapping("/pets")
    public List<Pet> findAll(Optional<String> name,
                             Optional<Integer> age) {

        return petService.findAll(name, age);
    }

    private Predicate<Pet> filterByName(String name) {
        return pet -> pet.getName().equals(name);
    }

    private Predicate<Pet> filterByAge(Integer age) {
        return pet -> pet.getAge().equals(age);
    }

    @PostMapping("/pets")
    public ResponseEntity<?> createPet(@RequestBody PetInputDto dto) {

        val created = petService.createPet(dtoConverter.toModel(dto));

        return ResponseEntity.created(uriBuilder.build(created.getId())).build();
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<?> updatePet(@RequestBody PetInputDto dto,
                                       @PathVariable Integer id) {
        var pet = petService.findById(id).orElseThrow(NoSuchPetException::new);
        dtoConverter.update(pet, dto);
        petService.save(pet);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pets/swap-owners/{firstId}/{secondId}")
    public void swapOwners(@PathVariable Integer firstId, @PathVariable Integer secondId) throws Exception {
        petService.swapOwners(firstId, secondId);
    }

    @PatchMapping("/pets/{id}")
    @Retryable(StaleObjectStateException.class)
    public void patchPet(@RequestBody PetInputDto dto,
                         @PathVariable Integer id) {
        val pet = petService.findById(id).get();

        dtoConverter.update(pet, dto);

        petService.save(pet);

    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletePet(@PathVariable Integer id) {
        petService.delete(id);
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public void noSuchPet(NoSuchPetException ex) {

    }

}

