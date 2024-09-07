package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gpa;
import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gradeThreePlus;

// power of streams!
public class StreamExample {
    static Function<Map<String, List<String>>, String> mapSoutFunc = (m) -> {
        return " - size=" + m.size() + ", contents=" + m;
    };

    public static void main(String[] args) {
        Map<String, List<String>> m1 = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println("PURE MAPPING" + mapSoutFunc.apply(m1));

        System.out.println("===");
        Map<String, List<String>> m2 = StudentDataBase.getAllStudents()
                .stream()
                .filter(gradeThreePlus)
                .collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println("gradeThreePlus" + mapSoutFunc.apply(m2));

        System.out.println("===");
        Map<String, List<String>> m3 = StudentDataBase.getAllStudents()
                .stream()
                .filter(gradeThreePlus)
                .filter(gpa)
                .collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println("gradeThreePlus_AND_GPA" + mapSoutFunc.apply(m3));
    }
}
