package org.ravi.educative;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.StringJoiner;

// https://www.educative.io/collection/page/5642554087309312/5679846214598656/70003
// modified to convert into a true LinkedList
public class MyLinkList<T> implements TheLinkedList<T> {
    private Node<T> head;
    private Node<T> last;
    private int length;

    public MyLinkList(T value) {
        head = Node.of(value);
        last = head;
        length++;
    }

    @Override
    public String display() {
        Node<T> tmp = head;
        StringJoiner joiner = new StringJoiner(" ");
        while (tmp != null) {
            joiner.add(tmp.getPayload().toString());
            tmp = tmp.getNext();
        }
        return joiner.toString();
    }

    @Override
    public T valueAt(int index) {
        return nodeAt(index).getPayload();
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void append(T value) {
        initLast();

        Node<T> node = Node.of(value);
        last.setNext(node);
        last = node;
        length++;
    }

    @Override
    public void prepend(T value) {
        Node<T> toAdd = Node.of(value);

        toAdd.setNext(head);
        head = toAdd;
        length++;
    }

    @Override
    public T insert(T value, int index) {
        if (index >= length) {
            append(value);
            return last.getPayload();
        }

        Node<T> toAdd = Node.of(value);
        Node<T> prev = nodeAt(index - 1);
        Node<T> tmp = prev.getNext();
        prev.setNext(toAdd);
        toAdd.setNext(tmp);
        length++;

        return toAdd.getPayload();
    }

    @Override
    public T remove(int index) {
        Node<T> prev = nodeAt(index - 1);
        Node<T> toRemove = prev.getNext();
        prev.setNext(toRemove.getNext());

        length--;
        return toRemove.getPayload();
    }

    @Override
    @WorthLooking("reverse linked list using 3 variables in a loop")
    public void reverse() {
        Node<T> remaining = head.getNext();
        Node<T> reversed = head;
        reversed.setNext(null);

        while (remaining != null) {
            Node<T> temp = remaining;
            remaining = remaining.getNext();

            temp.setNext(reversed);
            reversed = temp;
        }

        this.head = reversed;
        this.last = null;
    }

    // recursively walk down to the last
    // when no more start printing?
    @Override
    public String printReverse() {
        throw new RuntimeException("TODO - printReverse");
    }

    @Override
    public void reverseRecursive() {
        this.head = reverseRecursive(head);
        this.last = null;
    }

    private void initLast() {
        if (last == null) {
            Node<T> tmpLast = head;
            while (tmpLast.getNext() != null) {
                tmpLast = tmpLast.getNext();
            }
            last = tmpLast;
        }
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(" -> ", "[", "]");
        Node<T> node = head;

        joiner.add(node.getPayload().toString());
        while (node.getNext() != null) {
            joiner.add(node.getNext().getPayload().toString());
            node = node.getNext();
        }
        return joiner.toString();
    }

    private Node<T> nodeAt(int index) {
        if ((index < 0) || (index >= length)) {
            throw new RuntimeException("Bad index=" + index + ", while length=" + length);
        }

        int i = 0;
        Node<T> theNode = head;
        while (i != index) {
            theNode = theNode.getNext();
            i++;
        }

        return theNode;
    }

    @WorthLooking("recursive using a helper")
    private Node<T> reverseRecursive(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> nextNode = node.getNext();
        if (nextNode == null) {
            return node;
        }

        Node<T> reversed = reverseRecursive(nextNode);

        nextNode.setNext(node);
        node.setNext(null);
        return reversed;
    }
}
