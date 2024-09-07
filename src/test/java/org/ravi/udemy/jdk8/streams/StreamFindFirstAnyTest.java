package org.ravi.udemy.jdk8.streams;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;
import org.ravi.udemy.jdk8.data.Student;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamFindFirstAnyTest {
    public Optional<Student> findFirst(double gpa) {
        return StudentDataBase.getAllStudents().stream()
                .filter(student -> student.getGpa() >= gpa)
                .findFirst();
    }

    @WorthLooking("first and any are different in parallel streams ONLY")
    public Optional<Student> findAny(double gpa) {
        return StudentDataBase.getAllStudents().stream()
                .filter(student -> student.getGpa() >= gpa)
                .findFirst();
    }

    @Test
    public void globalTest() {
        Optional<Student> os = findFirst(4.0);
        assertThat(os.isPresent()).isTrue();
        assertThat(os.get().getName()).describedAs("first name check")
                .isEqualTo("Emily");

        os = findFirst(4.1);
        assertThat(os.isPresent()).isFalse();


        os = findAny(4.0);
        assertThat(os.isPresent()).isTrue();
        assertThat(os.get().getName()).describedAs("any name check")
                .isEqualTo("Emily");

        os = findAny(4.1);
        assertThat(os.isPresent()).describedAs("any with 4.1").isFalse();
    }
}
