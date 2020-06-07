package org.ravi.tlap;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagine a linked list where instead of the node storing a character, the node stores a digit: an int in the range 0–9. We could represent positive numbers of any size using such a linked list; the number 149, for example, would be a linked list in which the first node stores a 1, the second a 4, and the third and last a 9. Write a function intToList that takes an integer value and produces a linked list of this sort. Hint: You may find it easier to build the linked list backward, so if the value were 149, you would create the 9 node first.
 * <p>
 * p110, pdf=130
 */
public class IntList {
    // my idea:
    //  modulo 10 and keep adding to the arraylist
    // after done dividedNum == 0:
    //  start from list.size and create next pointers
    //
    // So 149 becomes node(9), node(4), node(1) with no next pointers
    //
    // RNTODO: build back the original number

    public static void main(String[] args) {
        int num = 149;
        System.out.printf("=== %d %n", num);
        printList(makeList(num));

        num = 7;
        System.out.printf("=== %d %n", num);
        printList(makeList(num));

        num = -36;
        System.out.printf("=== %d %n", num);
        printList(makeList(num));
    }

    public static IntNode makeList(int number) {
        int raw;
        List<IntNode> list = new ArrayList<>();

        raw = number;
        while (raw != 0) {
            int digit = raw % 10;
            raw = raw / 10;

            list.add(IntNode.of(digit));
        }

        IntNode head = null, tmp = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            IntNode node = list.get(i);
            if (head == null) {
                head = node;
            } else {
                tmp.setNext(node);
            }
            tmp = node;
        }

        return head;
    }

    public static void printList(IntNode head) {
        IntNode tmp = head;
        while (tmp != null) {
            System.out.printf("%d%n", tmp.getDigit());
            tmp = tmp.getNext();
        }
    }

    @Getter
    @Setter
    private static class IntNode {
        private final int digit;
        private IntNode next;

        private IntNode(int digit) {
            this.digit = digit;
        }

        public static IntNode of(int digit) {
            return new IntNode(digit);
        }
    }
}
