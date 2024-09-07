package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.stream.Collectors;

import static org.ravi.udemy.jdk8.streams.StreamsMapExample.studentSoutFunc;

// lab40 - filter operations
public class StreamsFilterExample {
    private final List<Student> students;

    public StreamsFilterExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamsFilterExample sfe = new StreamsFilterExample();

        System.out.println("female: " + studentSoutFunc.apply(sfe.byGender("female")));
        System.out.println(System.lineSeparator() + "=== ");
        System.out.println("male: " + studentSoutFunc.apply(sfe.byGender("male")));
    }

    public List<Student> byGender(String gender) {
        return students.stream()
                .filter(s -> s.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
