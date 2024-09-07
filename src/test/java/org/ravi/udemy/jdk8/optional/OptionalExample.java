package org.ravi.udemy.jdk8.optional;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Optional;

// lab72 - print student name example!
public class OptionalExample {

    public static String nullableStudentName() {
        Student student = StudentDataBase.random.nextInt(9) > 5 ?
                StudentDataBase.studentSupplier.get() : null;

        if (student != null) {
            System.out.println("Student (" + student.getName() + "), gr="
                    + student.getGradeLevel() + ", gpa=" + student.getGpa());
            return student.getName();
        }

        return null;
    }


    @WorthLooking("Optional.of ONLY IFF guaranteed value != null, else exception")
    static Optional<String> optionalStudentName() {
        Optional<Student> studentOptional = StudentDataBase.studentSupplierOptional.get();

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            System.out.println("optionalStudent (" + student.getName() + "), gr="
                    + student.getGradeLevel() + ", gpa=" + student.getGpa());
            return Optional.ofNullable(student.getName());
        }

        return Optional.empty();
    }

    @WorthLooking("Both are clumpsy. Optional:map and such method improve!")
    static void nullAwareNameLength() {
        String name = nullableStudentName();
        if (name != null) {
            System.out.println(name.length());
        } else {
            System.out.println("name does not exist");
        }
    }
    static void optionalNameLength() {
        Optional<String> optionalName = optionalStudentName();
        if (optionalName.isPresent()) {
            System.out.println(optionalName.get().length());
        } else {
            System.out.println("optional name does not exist");
        }
    }

    public static void main(String[] args) {
        nullAwareNameLength();

        optionalNameLength();
    }
}
