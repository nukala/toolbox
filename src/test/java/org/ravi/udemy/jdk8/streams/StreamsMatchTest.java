package org.ravi.udemy.jdk8.streams;

import org.junit.Test;
import org.ravi.udemy.jdk8.data.StudentDataBase;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsMatchTest {
    public boolean allMatch(double gpa) {
        return StudentDataBase.getAllStudents().stream()
                .allMatch(student -> student.getGpa() >= gpa);
    }

    public boolean anyMatch(double gpa) {
        return StudentDataBase.getAllStudents().stream()
                .anyMatch(student -> student.getGpa() >= gpa);
    }

    public boolean noneMatch(double gpa) {
        return StudentDataBase.getAllStudents().stream()
                .noneMatch(student -> student.getGpa() >= gpa);
    }

    @Test
    public void testAllMatch() {
        assertThat(allMatch(3.5)).describedAs("all 3.5").isTrue();

        assertThat(allMatch(4.0)).describedAs("all 4.0").isFalse();
    }


    @Test
    public void testAnyMatch() {
        assertThat(anyMatch(3.5)).describedAs("any 3.5").isTrue();

        assertThat(anyMatch(4.1)).describedAs("any 4.1").isFalse();
    }

    @Test
    public void testNoneMatch() {
        assertThat(noneMatch(4.1)).describedAs("none 4.1").isTrue();

        assertThat(noneMatch(4.0)).describedAs("none 4.0").isFalse();
    }
}
