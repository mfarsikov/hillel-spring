package hillel.spring.simpleapp;

import java.util.List;
import java.util.stream.Collectors;

import hillel.spring.Apple;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class SimpleApp {
    public static void main(String[] args) {

        val ctx = new AnnotationConfigApplicationContext("hillel.spring.simpleapp");

        val appleController = ctx.getBean(AppleController.class);

        appleController.printHeavyApples();
    }
}

@Controller
@AllArgsConstructor
class AppleController {
    private final AppleService appleService;

    public void printHeavyApples() {
        System.out.println(appleService.findHeavyApples());
    }
}

@Service
@AllArgsConstructor
class AppleService {
    private final AppleRepo repo;

    public List<Apple> findHeavyApples() {
        return repo.findAll().stream()
                   .filter(apple -> apple.getWeight() > 100)
                   .collect(Collectors.toList());
    }
}

@Repository
class AppleRepo {
    private final List<Apple> apples = List.of(
            new Apple(100, "Green"),
            new Apple(120, "Red")
    );

    public List<Apple> findAll() {
        return apples;
    }
}
