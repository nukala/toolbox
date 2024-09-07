package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;
import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gpa;
import static org.ravi.udemy.jdk8.PredicateAndConsumersExample.gradeThreePlus;
import static org.ravi.udemy.jdk8.streams.StreamExample.mapSoutFunc;

/**
 * Demonstrates threads
 * <li>are allotted for each element</li>
 * <li>filtering happens in different thread</li>
 * <li>also demonstrates peek</li>
 */
public class ParallelStreamExample {
    public static void main(String[] args) {
        Predicate<Student> tidGrGpaPred = (s) -> {
            if (gradeThreePlus.and(gpa).test(s)) {
                System.out.println("pred" + currentThread().getName() + " - TRUE, "
                        + s.getName() + ", gr=" + s.getGradeLevel() + ", gpa=" + s.getGpa());
                return true;
            } else {
                System.out.println("pred" + currentThread().getName() + " - FALSE, "
                        + s.getName() + ", gr=" + s.getGradeLevel() + ", gpa=" + s.getGpa());
                return false;
            }
        };

        System.out.println("===");
        @WorthLooking("size=6, creates 5 more threads, also note thread-element pairing remains.")
        Map<String, List<String>> m4 = StudentDataBase.getAllStudents()
                .parallelStream()
                .peek(student -> {
                    //@WorthLooking("threads have been allocated already")
                    System.out.println("peekAfterParallel: " + currentThread().getName() + ", " + student.getName());
                })
                .filter(tidGrGpaPred)
                .peek(student -> System.out.println("after filter:  " + currentThread().getName()
                        + ", " + student))
                .collect(Collectors.toMap(Student::getName, Student::getActivities));
        System.out.println("parallel_GR3_AND_GPA" + mapSoutFunc.apply(m4));
    }
}
