package org.ravi.udemy.jdk8;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {

        List<Student> allStudents = StudentDataBase.getAllStudents();
        Supplier<Integer> rand = () -> (int) (Math.random() * allStudents.size());

        int i = 0;
        System.out.println(++i + ". randomStudent = " + allStudents.get(rand.get()));
        System.out.println(++i + ". randomStudent = " + allStudents.get(rand.get()));
        System.out.println(++i + ". randomStudent = " + allStudents.get(rand.get()));
        System.out.println("====");

        Supplier<List<Student>> listSupplier = () -> allStudents;
        System.out.println("listSupplier: " + listSupplier.get());
    }
}
