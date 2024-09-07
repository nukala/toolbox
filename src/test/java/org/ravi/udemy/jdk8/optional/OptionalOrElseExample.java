package org.ravi.udemy.jdk8.optional;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Optional;

import static org.ravi.udemy.jdk8.streams.parallel.ParallelStreamExample.timedInvokation;

// lab74 various orElse optional methods
public class OptionalOrElseExample {
    static String nameWithOrElse() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        String name = studentOptional
                .map(Student::getName)
                .orElse("default");
        System.out.println("nameWithOrElse, name=" + name);
        return name;
    }

    @WorthLooking("when-null value comes from supplier")
    static String withOrElseGet() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        String name = studentOptional
                .map(Student::getName)
                .orElseGet(() -> "oeg-default");
        System.out.println("orElseGet, name=" + name);
        return name;
    }

    static String withOrElseThrow() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        try {
            String name = studentOptional
                    .map(Student::getName)
                    .orElseThrow(() -> new RuntimeException("No student"));
            System.out.println("orElseThrow - name=" + name);
            return name;
        } catch (Exception e) {
            System.out.println("orElseThrow - exception=" + e.getMessage());
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        int numLoops = 6;
        timedInvokation(
                OptionalOrElseExample::nameWithOrElse, numLoops);

        System.out.println(System.lineSeparator() + "===");
        timedInvokation(
                OptionalOrElseExample::withOrElseGet, numLoops);

        System.out.println(System.lineSeparator() + "===");
        timedInvokation(
                OptionalOrElseExample::withOrElseThrow, numLoops);
    }
}
