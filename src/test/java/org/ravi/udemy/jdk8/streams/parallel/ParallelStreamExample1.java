package org.ravi.udemy.jdk8.streams.parallel;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.stream.Collectors;

import static org.ravi.udemy.jdk8.streams.parallel.ParallelStreamExample.timedInvokation;

// lab69
public class ParallelStreamExample1 {
    public static void main(String[] args) {
        ParallelStreamExample1 se = new ParallelStreamExample1();
        int numLoops = 1;

        System.out.println("sequential duration="
                + timedInvokation(se::sequentialDistinctSortedActivities, numLoops));

        System.out.println("  parallel duration="
                + timedInvokation(se::parallelDistinctSortedActivities, numLoops));
    }

    List<String> sequentialDistinctSortedActivities() {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    List<String> parallelDistinctSortedActivities() {
        return StudentDataBase.getAllStudents().stream()
                .parallel()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
