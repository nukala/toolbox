package org.ravi.educative;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.ravi.rutils.ListUtils.from;
import static org.ravi.rutils.ListUtils.randomInts;

public class LinkedListTest {
    @Test
    public void randomDisplay() {
        System.out.printf("%s %n", randomInts(4).display());
    }

    @Test
    public void stringArray() {
        String aryStr = from(new String[]{"this", "is", "a", "test", "with", "array", "via", "iterator"})
                .display();
        assertThat(aryStr)
                .isEqualTo("this is a test with array via iterator");
        System.out.printf("%s %n", aryStr);
    }

    @Test
    public void intsVarArgs() {
        String intsStr = from(7, 14, 21, 28, 35).display();
        assertThat(intsStr)
                .isEqualTo("7 14 21 28 35");
        System.out.printf("%s %n", intsStr);
    }


    @Test
    public void appendThenVerifyLength() {
        TheLinkedList<Integer> list = randomInts(4);
        assertThat(list.getLength())
                .as("before append")
                .isEqualTo(4);

        list.append(5);
        assertThat(list.getLength())
                .isEqualTo(5);
    }

    @Test
    public void prependTest() {
        TheLinkedList<Integer> list = randomInts(4);

        int valueToAdd = 12345;
        list.prepend(valueToAdd);
        assertThat(list.getLength())
                .isEqualTo(5);
        assertThat(list.valueAt(0))
                .isEqualTo(valueToAdd);
    }

    @Test
    public void valueAtTests() {
        TheLinkedList<String> list = from("Hello", "from", "Fremont");

        assertThat(list.valueAt(0))
                .isEqualTo("Hello");
        assertThat(list.valueAt(2))
                .isEqualTo("Fremont");

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.valueAt(-1))
                .withMessageContaining("Bad index")
                .withMessageContaining("-1")
                .withMessageContaining("3");

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.valueAt(99))
                .withNoCause()
                .withMessageContaining("99");
    }

    @Test
    public void testInsert() {
        TheLinkedList<String> list = from("Hello", "from", "Fremont");

        list.insert("!!", 1);
        assertThat(list.valueAt(0))
                .isEqualTo("Hello");
        assertThat(list.valueAt(1))
                .isEqualTo("!!");

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.insert("Yo", -1))
                .withNoCause();
        list.insert(", CA", 99);
        assertThat(list.valueAt(list.getLength() - 1))
                .isEqualTo(", CA");
    }

    @Test
    public void testRemove() {
        TheLinkedList<String> list = from("Hello", "from", "Fremont");

        String removed = list.remove(list.getLength() - 1);
        assertThat(removed)
                .isEqualTo("Fremont");
        assertThat(list.getLength())
                .isEqualTo(2);

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.remove(-12));
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> list.remove(99));
    }
}
