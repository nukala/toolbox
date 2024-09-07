package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumersExample {
    @WorthLooking("no parantheses and no braces AND combos")
    public static final Predicate<Student> gradeThreePlus = s -> s.getGradeLevel() >= 3;
    public static final Predicate<Student> gpa = s -> s.getGpa() >= 3.9;
    public static final BiConsumer<String, List<String>> biConsumer = (n, l) ->
            System.out.println("biConsumer name=" + n + ": " + l);

    public static void main(String[] args) {
        new PredicateAndConsumersExample()
                .gradeAndGpa(StudentDataBase.getAllStudents());
    }

    private Consumer<Student> getChecker() {
        return s -> {
            if (gradeThreePlus.and(gpa).test(s)) {
                biConsumer.accept(s.getName(), s.getActivities());
            }
        };
    }

    public void gradeAndGpa(List<Student> students) {
        students.forEach(getChecker());
    }
}
