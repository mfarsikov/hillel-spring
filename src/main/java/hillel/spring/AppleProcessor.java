package hillel.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import lombok.val;

public class AppleProcessor {
    public static Optional<Apple> pickHeaviest(List<Apple> apples) {
        Apple heaviest = null;
        for (var apple : apples) {
            if (heaviest == null || heaviest.getWeight() < apple.getWeight()) {
                heaviest = apple;
            }
        }
        return Optional.ofNullable(heaviest);
    }

    public static <T> List<T> filter(List<T> objects, Predicate<T> predicate) {
        val appropriateApples = new ArrayList<T>();
        for (T thing : objects) {
            if (predicate.test(thing)) {
                appropriateApples.add(thing);
            }
        }
        return appropriateApples;
    }

    Optional<String> f(){
        if(Math.random() > 0.5){
            return Optional.of("Prize  here");
        }else {
            return Optional.empty();
        }
    }

}
/*

@FunctionalInterface
interface MyPredicate<T>{
    boolean test(T thing);
}*/
