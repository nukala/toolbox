package org.ravi.udemy.jdk8;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

// map of students and their gpas AND predicate
public class StudentsMap {

    public static void main(String[] args) {
        List<Student> students = StudentDataBase.getAllStudents();
        StudentsMap sm = new StudentsMap();

        System.out.println("gpaMap(true)=" + sm.buildMap(students, x -> true));
        System.out.println("gpaMap(gradeThreePlus)=" +
                sm.buildMap(students, PredicateAndConsumersExample.gradeThreePlus));
        System.out.println("gpaMap(false)=" + sm.buildMap(students, x -> false));
    }

    // see BiFunctionWithPredicate for simpler version
    private Map<String, Double> buildMap(List<Student> studentList, Predicate<Student> pred) {
        Objects.requireNonNull(pred, "non-null `pred` required");
        Function<List<Student>, Map<String, Double>> gpaMapFunc = (students -> {
            Map<String, Double> map = new HashMap<>();

            students.forEach(s -> {
                if (pred.test(s)) {
                    map.put(s.getName(), s.getGpa());
                }
            });
            return map;
        });

        return gpaMapFunc.apply(studentList);
    }
}
