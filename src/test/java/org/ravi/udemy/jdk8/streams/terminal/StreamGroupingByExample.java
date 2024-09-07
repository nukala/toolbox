package org.ravi.udemy.jdk8.streams.terminal;

import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

// many labs = 61,2,3,4
public class StreamGroupingByExample {
    public static final Function<Map<String, List<Student>>, String> mapSoutFunc =
            (m) -> m.getClass().getCanonicalName() + ", values=" + m;
    private final List<Student> students;

    public StreamGroupingByExample() {
        this.students = StudentDataBase.getAllStudents();
    }

    public static void main(String[] args) {
        StreamGroupingByExample se = new StreamGroupingByExample();

        System.out.println("byGender=" + mapSoutFunc.apply(se.byGender()));
        System.out.println(System.lineSeparator() + "===");
        System.out.println("customized=" + mapSoutFunc.apply(se.customizedGrouping()));
        System.out.println(System.lineSeparator() + "===");
        System.out.println("byGrade=" + mapSoutFunc.apply(se.byGrade()));
        System.out.println(System.lineSeparator() + "===");
        System.out.println("gradeAndCustom=" + se.gradeAndCustom());
        System.out.println(System.lineSeparator() + "===");
        System.out.println("nameAndBooks=" + se.nameAndBooks());

        se.genderLhmSet();

        se.calculateLowestGpaByGrade();
    }

    public Map<String, List<Student>> byGender() {
        return students.stream()
                .collect(groupingBy(Student::getGender));
    }

    public Map<String, List<Student>> byGrade() {
        return students.stream()
                // concatenation to accommodate the function above.
                .collect(groupingBy(s -> "" + s.getGradeLevel()));
    }

    public Map<String, List<Student>> customizedGrouping() {
        double threshold = 3.9;

        return students.stream()
                .collect(groupingBy(s -> s.getGpa() >= threshold ? "OUTSTANDING" : "AVERAGE"));
    }

    public Map<Integer, Map<String, List<Student>>> gradeAndCustom() {
        return students.stream()
                .collect(groupingBy(Student::getGradeLevel,
                        groupingBy(s -> s.getGpa() >= 3.8 ? "OUTSTANDING" : "AVERAGE")));
    }

    public Map<String, Integer> nameAndBooks() {
        return students.stream()
                .collect(groupingBy(Student::getName,
                        summingInt(Student::getNoteBooks)));
    }

    public void genderLhmSet() {
        Map<String, Set<Student>> setMap = students.stream()
                .collect(groupingBy(Student::getGender
                        , LinkedHashMap::new
                        , toSet()));

        System.out.println(System.lineSeparator() + "===" + System.lineSeparator() +
                setMap.getClass().getCanonicalName());
        System.out.println("genderLhmSet=" + setMap);
    }

    public void calculateLowestGpaByGrade() {
        @WorthLooking("Usage of andThen collector with supplier!")
        Map<Integer, Student> gradeLowGpaMap = students.stream()
                .collect(groupingBy(Student::getGradeLevel,
                        collectingAndThen(
                                minBy(Comparator.comparing(Student::getGpa)),
                                Optional::get)));

        System.out.println(System.lineSeparator() + "===" + System.lineSeparator() +
                gradeLowGpaMap.getClass().getCanonicalName());
        System.out.println("gradeLowGpaMap=" + gradeLowGpaMap);
    }
}
