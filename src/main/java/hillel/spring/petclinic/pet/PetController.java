package hillel.spring.petclinic.pet;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import hillel.spring.petclinic.pet.dto.PetDtoConverter;
import hillel.spring.petclinic.pet.dto.PetInputDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class PetController {
    private final PetService petService;
    private final PetDtoConverter dtoConverter;

    private final UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance()
                                                                        .scheme("http")
                                                                        .host("localhost")
                                                                        .path("/pets/{id}");

    @GetMapping("/pets/{id}")
    public Pet findById(@PathVariable Integer id) {
        val mayBePet = petService.findById(id);

        return mayBePet.orElseThrow(PetNotFoundException::new);
    }

    @GetMapping("/pets")
    public List<Pet> findAll(Optional<String> name,
                             Optional<Integer> age) {

        Optional<Predicate<Pet>> maybeNamePredicate = name.map(this::filterByName);
        Optional<Predicate<Pet>> maybeAgePredicate = age.map(this::filterByAge);

        Predicate<Pet> predicate = Stream.of(maybeAgePredicate, maybeNamePredicate)
                                         .flatMap(Optional::stream)
                                         .reduce(Predicate::and)
                                         .orElse(pet -> true);

        return petService.findAll(predicate);
    }

    private Predicate<Pet> filterByName(String name) {
        return pet -> pet.getName().equals(name);
    }

    private Predicate<Pet> filterByAge(Integer age) {
        return pet -> pet.getAge().equals(age);
    }

    @PostMapping("/pets")
    public ResponseEntity<?> createPet(@RequestBody PetInputDto dto) {

        val pet = dtoConverter.toModel(dto);
        pet.setId(22);

        petService.createPet(pet);

        return ResponseEntity.created(uriBuilder.build(pet.getId())).build();
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet,
                                       @PathVariable Integer id) {
        if (!pet.getId().equals(id)) {
            throw new IdMissmatchException();
        }
        petService.update(pet);
        return ResponseEntity.ok().build();

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

