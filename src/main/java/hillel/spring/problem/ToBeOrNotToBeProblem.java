package hillel.spring.problem;

public class ToBeOrNotToBeProblem implements Problem {

    @CorrectAnswer
    @Override
    public String simpleSolution() {
        return "Correct, it is obvious";
    }

    @Override
    public String complexSolution() {
        return "Wrong, it is much simpler than you think";
    }
}
