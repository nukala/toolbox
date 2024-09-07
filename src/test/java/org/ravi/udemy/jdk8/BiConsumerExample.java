package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.ravi.udemy.jdk8.data.StudentDataBase.getAllStudents;

public class BiConsumerExample {
    @WorthLooking("BiConsumer is not a consumer, hence need accept to create a new CONSUMER")
    public static void namesAndActivitiesAfterSecondGrade() {
        BiConsumer<String, List<String>> bc = (n, a) -> {
            System.out.printf(">2nd grade, %s, learns %s %n", n, a);
        };
        Consumer<Student> secondGradeBiconsumerToConsumer = (student) -> {
            if (student.getGradeLevel() > 2) {
                bc.accept(student.getName(), student.getActivities());
            }
        };

        getAllStudents()
                .forEach(secondGradeBiconsumerToConsumer);
    }

    public static void namesAndActivities() {
        BiConsumer<String, List<String>> biConsumer = (name, activites) -> {
            System.out.println("name=[" + name + "] activities=" + activites);
        };

        getAllStudents()
                .forEach(student ->
                        // this creates another biconsumer distinct from above!
                        biConsumer.accept(student.getName(), student.getActivities()));
    }


    public static void main(String[] args) {
        BiConsumer<String, String> show = (a, b) -> System.out.println("a=" + a + ", b=" + b);
        show.accept("java7", "java8");

        @WorthLooking("arithmetic operations on Integer object")
        BiConsumer<Integer, Integer> multiply = (a, b) ->
                System.out.println("multiply(" + a + "*" + b + ") = " + (a * b));
        @WorthLooking("andThen is NOT pipelinig, it occurs on the same inputs")
        BiConsumer<Integer, Integer> divide = (a, b) ->
                System.out.println("divide(" + a + "/" + b + ") = " + (a / b));
        multiply.andThen(divide).accept(10, 5);

        namesAndActivitiesAfterSecondGrade();
        namesAndActivities();
    }
}
