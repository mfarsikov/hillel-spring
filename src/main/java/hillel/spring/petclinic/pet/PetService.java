package hillel.spring.petclinic.pet;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Optional<Pet> findById(Integer id) {
        return petRepository.findById(id);
    }

    public List<Pet> findAll(Optional<String> name, Optional<Integer> age
    ) {

        if (name.isPresent() && age.isPresent()) {
            return petRepository.findByNameAndAge(name.get(), age.get());
        }
        if (name.isPresent()) {
            return petRepository.findByName(name.get());
        }
        if (age.isPresent()) {
            return petRepository.findByAge(age.get());
        }
        return petRepository.findAll();
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public void save(Pet pet) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) { }

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
        firstPet.setOwner(secondPet.getOwner());
        secondPet.setOwner(firstOwner);

        petRepository.save(firstPet);
        petRepository.save(secondPet);
    }
}
