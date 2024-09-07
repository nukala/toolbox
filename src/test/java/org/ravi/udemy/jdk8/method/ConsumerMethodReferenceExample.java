package org.ravi.udemy.jdk8.method;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.function.Consumer;

public class ConsumerMethodReferenceExample {

    static Consumer<Student> studentConsumer = (s) -> System.out.println(s.getActivities());

    @WorthLooking("Need special method in Student ... since it is more than method call")
    static Consumer<Student> consumerReference = Student::printListOfActivities;

    public static void main(String[] args) {
        StudentDataBase.getAllStudents().forEach(studentConsumer);
        System.out.println("===");
        StudentDataBase.getAllStudents().forEach(consumerReference);
    }
}
