package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

// lab59 - min/max by custom comparator!
public class StreamMaxByMinByExample {
    static Function<Optional<Student>, String> optStudentFunc = (os) ->
            os.map(student -> "PRESENT, value=" + student)
            .orElseGet(() -> "empty=" + ", no value");
    private final List<Student> students;

    public StreamMaxByMinByExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamMaxByMinByExample se = new StreamMaxByMinByExample();

        System.out.println("maxByGpa=" + optStudentFunc.apply(se.maxByGpa()));
        System.out.println("minByGpa=" + optStudentFunc.apply(se.minByGpa()));

    }

    public Optional<Student> maxByGpa() {
        return students.stream()
                .collect(maxBy(Comparator.comparing(Student::getGpa)));
    }

    public Optional<Student> minByGpa() {
        return students.stream()
                .collect(minBy(Comparator.comparing(Student::getGpa)));
    }
}
