package hillel.spring.petclinic.store;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @PostMapping("/store")
    public void createDrug(@Valid @RequestBody DrugInputDto drug){
        System.out.println("SUCCESS");
    }

}
