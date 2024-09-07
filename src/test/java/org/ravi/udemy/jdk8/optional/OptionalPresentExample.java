package org.ravi.udemy.jdk8.optional;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;
import org.ravi.udemy.jdk8.streams.parallel.ParallelStreamExample;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.ravi.udemy.jdk8.streams.parallel.ParallelStreamExample.timedInvokation;

public class OptionalPresentExample {

    @WorthLooking("supplier cannot return void")
    static String withIsPresent() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        String name;
        if (studentOptional.isPresent()) {
            name = studentOptional.get().getName();
        } else {
            name = "EMPTY-default";
        }
        System.out.println("isPresent - name=" + name);
        return name;
    }

    @WorthLooking("ifPresent invoke consumer. Simplifies null-check code!")
    static String ifPresentDoConsume() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        final AtomicReference<String> stringRef =
                new AtomicReference<>("ifPresent - default");
        studentOptional.ifPresent(student -> stringRef.set(student.getName()));

        System.out.println("IF - name=" + stringRef.get());
        return stringRef.get();
    }

    public static void main(String[] args) {
        int numLoops = 4;
        timedInvokation(OptionalPresentExample::withIsPresent, numLoops);

        System.out.println(System.lineSeparator() + "==== ");
        timedInvokation(OptionalPresentExample::ifPresentDoConsume, numLoops);
    }
}
