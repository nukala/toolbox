package org.ravi.udemy.jdk8.streams;


import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * `map` method transforms stream of one object to another
 */
public class StreamsMapExample {
    public static Function<Collection<String>, String> strSoutFunc =
            (coll) -> coll.getClass().getCanonicalName() + ", values=" + coll;
    public static Function<Collection<Student>, String> studentSoutFunc =
            (coll) -> coll.getClass().getCanonicalName()
                    + ", values=" + System.lineSeparator() + "   " + coll;

    private final List<Student> students;

    public StreamsMapExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamsMapExample sme = new StreamsMapExample();
        System.out.println(strSoutFunc.apply(sme.namesList()));
        System.out.println("===");
        System.out.println(strSoutFunc.apply(sme.namesSet()));
    }

    public Set<String> namesSet() {
        return students.stream() // Stream<Student>
                .map(Student::getName)// Stream<String>
                .map(String::toUpperCase) // Stream<String>
                .collect(toSet());
    }

    public List<String> namesList() {
        return students.stream().parallel()
                .map(Student::getName)
                .map((str) -> {
                    //@WorthLooking("can use a real Function")
                    return str.toLowerCase();
                }) // Stream<String>
                .collect(toList());
    }
}
