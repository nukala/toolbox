package org.ravi.udemy.jdk8.streams;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

// lab39
public class StreamsComparatorExample {
    private final List<Student> students;

    public StreamsComparatorExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamsComparatorExample sce = new StreamsComparatorExample();
        System.out.println("sortByName : " + sce.sortByName());

        System.out.println("byGPA (increasing) ==");
        sce.sortByGpa().forEach(System.out::println);

        System.out.println(System.lineSeparator() + "sortByGpaDesc ==");
        sce.sortByGpaDesc().forEach(System.out::println);

        System.out.println(System.lineSeparator() + "sortByGpaDescNameAsc ==");
        sce.sortByGpaDescNameAsc().forEach(System.out::println);
    }

    // sort students by thier name.
    public List<Student> sortByName() {
        return students
                .stream()
                .sorted((o1, o2) -> {
                    // using a new comparator
                    return o1.getName().compareTo(o2.getName());
                })
                .collect(toList());
    }

    public List<Student> sortByGpa() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getGpa))
                .collect(Collectors.toList());
    }

    public List<Student> sortByGpaDesc() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .collect(Collectors.toList());
    }

    @WorthLooking("Comparator.comparing takes a Function ... lets chain that!")
    public List<Student> sortByGpaDescNameAsc() {
        return students
                .stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed()
                        .thenComparing(Comparator.comparing(Student::getName)))
                .collect(Collectors.toList());
    }
}
