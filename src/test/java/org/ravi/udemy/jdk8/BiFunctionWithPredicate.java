package org.ravi.udemy.jdk8;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gpa;
import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gradeThreePlus;

// List<Students> with predicate parameter
// students to gpa Map
public class BiFunctionWithPredicate {
    static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>>
            gpaPredBifunction = (students, pred) -> {
        Map<String, Double> map = new HashMap<>();

        students.forEach(student -> {
            if (pred.test(student)) {
                map.put(student.getName() + "[" + student.getGradeLevel() + "]", student.getGpa());
            }
        });
        return map;
    };


    public static void main(String[] args) {
        List<Student> allStudents = StudentDataBase.getAllStudents();

        System.out.println("true=" + gpaPredBifunction.apply(allStudents, (x -> true)));
        System.out.println("gradeThreePlus=" + gpaPredBifunction.apply(allStudents, gradeThreePlus));
        System.out.println("gpa=" + gpaPredBifunction.apply(allStudents, gpa));
        System.out.println("gpa.and(grade)=" + gpaPredBifunction.apply(allStudents,
                gpa.and(gradeThreePlus)));
    }
}
