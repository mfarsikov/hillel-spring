package hillel.spring.knight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

public class KnightApp {
    public static void main(String[] args) {

        val ctx = new AnnotationConfigApplicationContext("hillel.spring.knight");

        val knight = ctx.getBean( Knight.class);

        System.out.println(knight);
    }
}

@Configuration
class Config {

    @Bean
    public String horseName(){
        return "Rocenant";
    }

    @Bean
    public String knightName(){
        return "Keehot";
    }
}

@Component
@Data
class Knight {
    private final String name;
    private final Gear gear;

    private Horse horse;

    public Knight(@Qualifier("knightName") String name, Gear gear) {
        this.name = name;
        this.gear = gear;
    }

    @Autowired
    public void setHorse(Horse horse) {
        this.horse = horse;
    }
}

@Component
@Data
class Horse {
    private final String name;

    public Horse(@Qualifier("horseName") String name) {
        this.name = name;
    }
}

@Component
@Data
class Gear {
    private final Sword sword;
    private final Armor armor;
}

@Component
@Data
class Sword {
    private final String description = "Sharp sword";
}

@Component
@Data
class Armor {
    private final String description = "Shiny armor";
}