package hillel.spring.petclinic.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicUserDetailsRepository extends JpaRepository<ClinicUser, Integer> {

    ClinicUser findByUsername(String username);
}
