package org.ravi.udemy.jdk8.method;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.function.Predicate;

public class RefactorMethodReferenceExample {
    public static final Predicate<Student> gradeThreePlus = s -> s.getGradeLevel() >= 3;

    static final Predicate<Student> g3methodRef = RefactorMethodReferenceExample::isThreePlus;

    @WorthLooking("For extralogic, create a method in order to use reference!")
    public static boolean isThreePlus(Student s) {
        return gradeThreePlus.test(s);
    }

    public static void main(String[] args) {
        Student s = StudentDataBase.studentSupplier.get();

        System.out.println("pred - name=" + s.getName() + ", gr=" + s.getGradeLevel() +
                ", isThreePlus=" + gradeThreePlus.test(s));
        System.out.println("methodRef - name=" + s.getName() + ", gr=" + s.getGradeLevel() +
                ", isThreePlus=" + g3methodRef.test(s));

    }
}
