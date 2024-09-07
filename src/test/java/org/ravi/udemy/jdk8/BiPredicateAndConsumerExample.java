package org.ravi.udemy.jdk8;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * No need to chain predicates ... we can create pairs of 'em with BiPredicates
 */
public class BiPredicateAndConsumerExample {
    // cannot reuse predicates from parent class -- wrong types
    private final BiPredicate<Integer, Double> gradeAndGpaBiPredicate = (grade, gpa) ->
            grade >= 3 && gpa >= 3.9;

    public static void main(String[] args) {
        new BiPredicateAndConsumerExample()
                .gradeAndGpa(StudentDataBase.getAllStudents());
    }

    private void gradeAndGpa(List<Student> allStudents) {
        allStudents.forEach(getChecker());
    }

    private Consumer<Student> getChecker() {
        return (Student s) -> {
            if (gradeAndGpaBiPredicate.test(s.getGradeLevel(), s.getGpa())) {
                System.out.println("gradeAndGpaBiPredicate: " + s);
            }
        };
    }
}
