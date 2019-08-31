package hillel.spring.petclinic.pet;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    public Page<Pet> findAll(Optional<String> name,
                             Optional<Integer> age,
                             Pageable pageable
    ) {

        if (name.isPresent() && age.isPresent()) {
            return petRepository.findByNameAndAge(name.get(), age.get(), pageable);
        }
        if (name.isPresent()) {
            return petRepository.findByName(name.get(), pageable);
        }
        if (age.isPresent()) {
            return petRepository.findByAge(age.get(), pageable);
        }
        return petRepository.findAll(pageable);
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void save(Pet pet) {
        petRepository.save(pet);
    }

    public void delete(Integer id) {
        petRepository.deleteById(id);
    }

    public void swap2(){
        swapOwners(1,2);
    }

    @Transactional
    public void swapOwners(Integer firstId, Integer secondId) {
        val firstPet = petRepository.findById(firstId).get();
        val secondPet = petRepository.findById(secondId).get();

        val firstOwner = firstPet.getOwner();
        firstPet.setOwner(secondPet.getOwner().orElse(null));
        secondPet.setOwner(firstOwner.orElse(null));

        petRepository.save(firstPet);
        petRepository.save(secondPet);
    }
}
