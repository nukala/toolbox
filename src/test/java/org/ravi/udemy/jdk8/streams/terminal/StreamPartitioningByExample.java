package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;

// lab65
public class StreamPartitioningByExample {
    private final List<Student> students;

    public StreamPartitioningByExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    // dev_dot_java functional-interfaces
    private void usePredicateAsAnObject() {
        int len = 6; // almost final without declaring as such!
        final String pfx = " ".repeat(3);
        String[] ary = {"user", "should", "and", "system", "should", "are", "system", "design", "requirements"};
        Predicate<String> predicate = s -> s.length() < len;
        Consumer<String> pfxPrinter = s -> {
            System.out.print(pfx);
            System.out.printf("\"%s\" is < %d chars%n", s, len);
        };
        System.out.println("In: " + Arrays.toString(ary));
        for (String elem : ary) {
            if (predicate.test(elem)) {
                //System.out.printf("%s\"%s\" is %d chars %n", pfx, elem, len);
                pfxPrinter.accept(elem);
            }
        }
    }

    public static void main(String[] args) {
        StreamPartitioningByExample se = new StreamPartitioningByExample();

        se.byGenderPartition();
        se.byGenderCount();

        se.usePredicateAsAnObject();
    }
    Predicate<Student> genderPred = student -> student.getGender().equals("female");

    public void byGenderPartition() {

        Map<Boolean, List<Student>> genderList = students.stream()
                .collect(partitioningBy(genderPred));
        System.out.println("byGenderPartition (female=true)=" + genderList);
    }

    void byGenderCount() {
        Map<Boolean, Integer> genderCount = students.stream()
                .collect(partitioningBy(genderPred,
                        summingInt((students) -> 1)));

        System.out.println("genderCount (true=female)=" + genderCount);
    }
}
