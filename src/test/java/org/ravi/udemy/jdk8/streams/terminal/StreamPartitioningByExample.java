package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summingInt;

// lab65
public class StreamPartitioningByExample {
    private final List<Student> students;

    public StreamPartitioningByExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamPartitioningByExample se = new StreamPartitioningByExample();

        se.byGenderPartition();
        se.byGenderCount();
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
