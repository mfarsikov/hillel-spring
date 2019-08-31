package hillel.spring.petclinic.pet;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

    @Query("select pet from Pet as pet where  pet.name=:name and pet.age=:age")
    Page<Pet> findByNameAndAge(@Param("name") String name, @Param("age") Integer age, Pageable pageable);

    Page<Pet> findByName(String name, Pageable pageable);

    Page<Pet> findByAge(Integer age, Pageable pageable);
}
