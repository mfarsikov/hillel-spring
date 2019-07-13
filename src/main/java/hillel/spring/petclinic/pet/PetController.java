package hillel.spring.petclinic.pet;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/pets/{id}")
    public Pet findById(@PathVariable Integer id) {
        val mayBePet = petService.findById(id);

        return mayBePet.orElseThrow(PetNotFoundException::new);
    }

    @GetMapping("/pets")
    public List<Pet> findAll() {
        return petService.findAll();
    }

    @PostMapping("/pets")
    public void createPet(@RequestBody Pet pet) {
        petService.createPet(pet);
    }

    @PutMapping("/pets/{id}")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet,
                                       @PathVariable Integer id) {
        if (!pet.getId().equals(id)) {
            throw new IdMissmatchException();
        }
        try {
            petService.update(pet);
            return ResponseEntity.ok().build();
        } catch (NoSuchPetException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable Integer id){
        petService.delete(id);
    }
}

