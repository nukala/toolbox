package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.stream.Collectors;

//lab56
public class StreamsJoiningExample {
    private final List<Student> students;

    public StreamsJoiningExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamsJoiningExample se = new StreamsJoiningExample();
        System.out.println("noParams=" + se.noParams());
        System.out.println("delimiter=" + se.delimiter());
        System.out.println("allThree=" + se.allThree());
    }

    public String noParams() {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining());
    }
    public String delimiter() {
        return students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(", "));
    }
    public String allThree() {
        return students.stream()
                .filter(s -> s.getGender().equals("male"))
                .map(Student::getName)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
