package org.ravi.udemy.jdk8.defaults;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingDouble;
import static org.assertj.core.api.Assertions.assertThat;

// lab80 - sort operations using comparator
public class DefaultMethodTest {
    Comparator<Student> nameComparator = comparing(Student::getName);
    Comparator<Student> gpaComparator = comparingDouble(Student::getGpa);
    Comparator<Student> gradeComparator = comparing(Student::getGradeLevel);

    @Test
    public void sortByName() {
        List<Student> studentList = StudentDataBase.getAllStudents();
        studentList
                .sort(nameComparator);

        assertThat(studentList.getFirst().getName()).isEqualTo("Adam");
        assertThat(studentList.getLast().getName()).isEqualTo("Sophia");
    }

    @Test
    public void sortByGPA() {
        List<Student> studentList = StudentDataBase.getAllStudents();
        studentList
                .sort(gpaComparator);

        // assert Double comparison with tolerance
        assertThat(studentList.getFirst().getGpa())
                .isEqualTo(3.5, Assertions.within(0.01));
        assertThat(studentList.getLast().getGpa()).isEqualTo(4.0,
                Offset.offset(0.01));
    }

    @Test
    @WorthLooking("Comparator chaining with thenComparing")
    public void chainComparators() {
        List<Student> studentList = StudentDataBase.getAllStudents();
        studentList
                .sort(gradeComparator.thenComparing(nameComparator));
        studentList.forEach(System.out::println);

        assertThat(studentList.get(2).getGradeLevel()).isEqualTo(3);
        assertThat(studentList.get(2).getName()).isEqualTo("Dave");
        assertThat(studentList.getLast().getName()).isEqualTo("Sophia");
    }

    @Test
    public void sortWithNullValues() {
        List<Student> studentList = new ArrayList<>();
        studentList.addFirst(null);
        studentList.addAll(StudentDataBase.getAllStudents());
        studentList.add(null);

        // verify nulls got added properly
        assertThat(studentList.getFirst())
                .describedAs("before sort first").isNull();
        assertThat(studentList.getLast())
                .describedAs("before sort last").isNull();
        assertThat(studentList.size()).isEqualTo(8);

        studentList.sort(Comparator.nullsFirst(nameComparator));

        // now first two are null, then adam -> dave
        assertThat(studentList.getFirst()).isNull();
        assertThat(studentList.get(1)).isNull();
        assertThat(studentList.size()).isEqualTo(8);
        assertThat(studentList.get(2).getName()).isEqualTo("Adam");
        assertThat(studentList.get(3).getName()).isEqualTo("Dave");
    }
}
