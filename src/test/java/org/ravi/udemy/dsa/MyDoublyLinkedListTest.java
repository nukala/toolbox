package org.ravi.udemy.dsa;

import org.junit.Test;
import org.ravi.educative.TheLinkedList;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.ravi.rutils.ListUtils.dblFrom;

// display checked prior to implementing valueAt
public class MyDoublyLinkedListTest {
    private static Random random = new Random();

    @Test
    public void randomLongs() {
        TheLinkedList<Long> list = dblFrom(4,
                () -> random.nextLong());

        assertThat(list.getLength())
                .isEqualTo(4);
        System.out.printf("%s%n", list.display());
    }

    @Test
    public void threeNumbers() {
        TheLinkedList<Integer> list = new MyDoublyLinkedList<>(1);
        list.append(2);
        list.append(3);

        String disp = list.display();
        assertThat(disp)
                .contains("1 >> 2 >> 3")
                .contains("3 << 2 << 1");
        System.out.printf("DoublyLinkedList = %s%n", disp);
    }

    @Test
    public void fromArray() {
        TheLinkedList<String> list = dblFrom(new String[]{"This", "is", "me"});

        assertThat(list.getLength())
                .isEqualTo(3);
    }

    @Test
    public void fromSentence() {
        TheLinkedList<String> list = dblFrom("Fremont is in California");

        assertThat(list.getLength())
                .isEqualTo(4);
        String disp = list.display();
        System.out.printf("%s%n", disp);
        assertThat(disp)
                .contains("Fremont >> is >> in >> California")
                .contains("California << in << is << Fremont");
    }

    @Test
    public void prependTest() {
        TheLinkedList<String> list = dblFrom("is in California");

        list.prepend("Fremont");

        assertThat(list.getLength())
                .isEqualTo(4);
        String disp = list.display();
        System.out.printf("%s%n", disp);
        assertThat(disp)
                .contains("Fremont >> is >> in >> California");

    }

    @Test
    public void insertTest() {
        TheLinkedList<Integer> list = dblFrom(1, 3, 4, 5);

        assertThat(list.getLength())
                .isEqualTo(4);
        list.insert(2, 1);
        String disp = list.display();
        System.out.printf("%s%n", disp);
        assertThat(disp)
                .contains("1 >> 2 >> 3 >> 4 >> 5")
                .contains("5 << 4 << 3 << 2 << 1");
        assertThat(list.getLength())
                .isEqualTo(5);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.insert(0, -1));
        list.insert(6, 99);
        disp = list.display();
        System.out.printf("After insert 6@99 = %s%n", disp);
        assertThat(disp)
                .contains("5 >> 6")
                .contains("6 << 5");

        list.insert(-1, 5);
        disp = list.display();
        System.out.printf("After insert -1@5 = %s%n", disp);
        assertThat(disp)
                .contains("5 >> -1 >> 6")
                .contains("6 << -1 << 5");
    }

    @Test
    public void removeTest() {
        TheLinkedList<String> list = dblFrom("Ultimate coding interview bootcamp");

        assertThat(list.getLength())
                .isEqualTo(4);

        String removed = list.remove(2);
        assertThat(removed)
                .isEqualTo("interview");
        System.out.printf("After removing = %s%n", list.display());
        assertThat(list.valueAt(1))
                .isEqualTo("coding");
        assertThat(list.getLength())
                .isEqualTo(3);
        assertThat(list.valueAt(2))
                .as("at 2 ")
                .isEqualTo("bootcamp");

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.remove(-1));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.remove(5));
    }
}
