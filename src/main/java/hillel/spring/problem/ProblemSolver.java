package hillel.spring.problem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.SneakyThrows;
import lombok.val;

public class ProblemSolver {
    public String solve(Problem problem) {

        val aClass = problem.getClass();
        val methods = aClass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(CorrectAnswer.class)) {
                return invoke(method, problem);
            }
        }
        throw new RuntimeException("There is no method marked as CorrectAnswer");
    }

    @SneakyThrows
    private String invoke(Method m, Object o) {
        return (String) m.invoke(o);
    }
}
