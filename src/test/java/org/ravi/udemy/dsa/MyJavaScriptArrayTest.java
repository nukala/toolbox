package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MyJavaScriptArrayTest {

    @Test
    public void pushAndPopTwo() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        myArray.push("pineapple");
        assertThat(myArray.getCount())
                .isEqualTo(1);
        myArray.push("orange");
        assertThat(myArray.getCount())
                .isEqualTo(2);
        assertThat(myArray.getLength())
                .isEqualTo(10);

        String popped = myArray.pop();
        assertThat(popped)
                .isEqualTo("orange");
        popped = myArray.pop();
        assertThat(popped)
                .isEqualTo("pineapple");
        assertThat(myArray.getCount())
                .isEqualTo(0);
        assertThat(myArray.getLength())
                .isEqualTo(10);
    }

    @Test
    public void popAtEmpty() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        assertThatIllegalStateException()
                .isThrownBy(myArray::pop);
    }

    @Test
    public void doubleThenPop() {
        TheArray<String> myArray = new MyJavaScriptArray<>(1);

        assertThat(myArray.getLength())
                .isEqualTo(1);
        myArray.push("one");
        assertThat(myArray.getLength())
                .isEqualTo(2);
        assertThat(myArray.getCount())
                .isEqualTo(1);

        myArray.push("hello");
        assertThat(myArray.getLength())
                .isEqualTo(4);
        assertThat(myArray.getCount())
                .isEqualTo(2);

        assertThat(myArray.pop())
                .isEqualTo("hello");
        assertThat(myArray.pop())
                .isEqualTo("one");
    }

    @Test
    public void pushThenPeekThenPop() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        myArray.push("hello").push("world");

        assertThat(myArray.peek())
                .isEqualTo("world");
        assertThat(myArray.getCount())
                .isEqualTo(2);

        myArray.pop();
        assertThat(myArray.peek())
                .isEqualTo("hello");
        assertThat(myArray.getCount())
                .isEqualTo(1);
    }

    @Test
    public void peekEmpty() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        assertThatIllegalStateException()
                .isThrownBy(myArray::peek);
    }

    @Test
    public void elementAt() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        myArray.push("hello").push("world").push("!!!");

        assertThat(myArray.getCount())
                .isEqualTo(3);
        assertThat(myArray.elementAt(0))
                .isEqualTo("hello");
        assertThat(myArray.elementAt(1))
                .isEqualTo("world");
        assertThat(myArray.elementAt(2))
                .isEqualTo("!!!");

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> myArray.elementAt(9));
    }

    @Test
    public void toStringTest() {
        TheArray<String> myArray = new MyJavaScriptArray<>();

        myArray.push("hello").push("world").push("!!!");

        String str = myArray.toString();
        assertThat(str)
                .contains("hello", "world", "!!!", "next=", "length=");
    }
}
