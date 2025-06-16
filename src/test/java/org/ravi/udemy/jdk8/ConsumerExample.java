package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;

import java.util.function.Consumer;

import static org.ravi.udemy.jdk8.RunnableLambdaExample.dbg;
import static org.ravi.udemy.jdk8.data.StudentDataBase.getAllStudents;

public class ConsumerExample {

    public static void printStudents() {
        @WorthLooking("forEach takes a consumer, need special work for biconsumer")
        Consumer<Student> c2 = student -> System.out.printf("c2=%s%n", student);
        getAllStudents()
                .forEach(c2);
    }

    @WorthLooking("For only one parameter, paranthesis are not required! just arrow")
    public static void studentsAndActivities() {
        getAllStudents().forEach(student ->
                System.out.printf("name=%s; activities=%s%n", student.getName(), student.getActivities()));
    }

    public static void main(String[] args) {
        Consumer<String> upperTid = (s) -> dbg(s.toUpperCase());
        upperTid.accept("java8");

        printStudents();
        System.out.println("\n== Names and activities ==\n");
        studentsAndActivities();
    }
}
