package org.ravi.udemy.jdk8.optional;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Bike;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Optional;

import static org.ravi.udemy.jdk8.streams.parallel.ParallelStreamExample.timedInvokation;

// lab76
public class OptionalMapFlatMapExample {

    public static void main(String[] args) {
        OptionalMapFlatMapExample oe = new OptionalMapFlatMapExample();

        int numLoops = 5;
        System.out.println("Each method is executed " + numLoops
                + " times. If there is no output -> either GPA is low OR empty-student");
        timedInvokation(oe::withOptionalFilter, numLoops);

        System.out.println(System.lineSeparator() + "====");
        timedInvokation(oe::withMap, numLoops);

        System.out.println(System.lineSeparator() + "randomBoolean hasbikes. ====");
        timedInvokation(oe::withFlatMap, numLoops);
    }

    // map
    public String withMap() {
        Optional<Student> optionalStudent = StudentDataBase.studentSupplierOptional.get();

        String name = optionalStudent.
                map(Student::getName)
                .orElse("null-student");
        System.out.println("withMap - name=" + name);
        return name;
    }

    // filter
    public String withOptionalFilter() {
        Optional<Student> optionalStudent = StudentDataBase.studentSupplierOptional.get();

        optionalStudent.
                filter(student -> student.getGpa() >= 3.6)
                .ifPresent(student -> System.out.println("withOptionalFilter - " + student));
        return "";
    }

    // flatmap
    @WorthLooking("use flatMap to open up the structure. Here Optional Student has Optional Bike")
    public String withFlatMap() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        Optional<String> optionalBikeName = studentOptional   // Optional<Student>
                .filter(student -> student.getGpa() >= 3.5) // Optional<Student>
                .flatMap(Student::getBike) // Optional<Bike>
                .map(Bike::getName); // Optional<String>
        
        optionalBikeName.ifPresent(s -> System.out.println("withFlatMap - bikeName=" + s));
        return "";
    }
}
