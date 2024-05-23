package org.ravi.educative;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.rutils.ListUtils.from;

//https://www.educative.io/collection/page/5642554087309312/5679846214598656/70003
//
// display checked prior to implementing valueAt
public class ReverseLinkedNodeTest {
    @Test
    public void reverseLoopTest() {
        TheLinkedList<Integer> linkList = from(7, 14, 21, 28);
        System.out.printf("Before = %s%n", linkList);
        linkList.reverse();
        System.out.printf("Reversed = %s%n", linkList);
        assertThat(linkList.display())
                .isEqualTo("28 21 14 7");
    }


    @Test
    public void bookExample() { // run it in debugger
        TheLinkedList<String> linkList = from("a1", "a2", "a3", "a4");
        System.out.printf("Before = %s%n", linkList);
        linkList.reverse();
        System.out.printf("Reversed = %s%n", linkList);
        assertThat(linkList.display())
                .isEqualTo("a4 a3 a2 a1");
    }

    @Test
    public void reverseRecursiveTest() {
        TheLinkedList<Integer> linkList = from(7, 14, 21, 28);
        //System.out.printf("Before = %s%n", head.display());
        linkList.reverseRecursive();
        System.out.printf("Reversed = [%s]%n", linkList.display());
        assertThat(linkList.display())
                .isEqualTo("28 21 14 7");
    }

    @Test
    public void reverseThreeTest() {
        TheLinkedList<String> list = from("We live in California");
        list.reverse();

        System.out.printf("%s%n", list);
        assertThat(list.display())
                .isEqualTo("California in live We");

        assertThat(list.getLength())
                .isEqualTo(4);
        assertThat(list.valueAt(0))
                .isEqualTo("California");
        assertThat(list.valueAt(list.getLength() - 1))
                .isEqualTo("We");
    }

    @Test(expected = RuntimeException.class)
    public void printReverseTest() {
        TheLinkedList<String> list = from("We live in California");

        assertThat(list.printReverse())
                .isEqualTo("California in live We");
    }
}
