package org.ravi.udemy.dsa;

import org.ravi.educative.DblNode;
import org.ravi.educative.TheLinkedList;

import java.util.StringJoiner;
import java.util.function.Function;


// started 1210, stopped=1227
// started 
public class MyDoublyLinkedList<T> implements TheLinkedList<T> {
    private DblNode<T> head;
    private DblNode<T> tail;
    private int length;

    public MyDoublyLinkedList(T value) {
        head = tail = new DblNode<>(value);

        length++;
    }

    @Override
    public void append(T value) {
        DblNode<T> newNode = new DblNode<>(value);

        newNode.setPrev(tail);

        tail.setNext(newNode);
        tail = newNode;

        length++;
    }

    private String displayHelper(DblNode<T> leader, String sep, Function<DblNode<T>, DblNode<T>> function) {
        StringJoiner joiner = new StringJoiner(sep);
        DblNode<T> curr = leader;
        while (curr != null) {
            joiner.add(curr.getValue().toString());
            curr = function.apply(curr);
        }

        return joiner.toString();
    }

    @Override
    public String display() {
        return ">> " + displayHelper(head, " >> ", curr -> curr.getNext())
                + System.lineSeparator()
                + displayHelper(tail, " << ", curr -> curr.getPrev()) + " <<";
    }

    @Override
    public T valueAt(int index) {
        return traverseToIndex(index).getValue();
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void prepend(T value) { // 240
        DblNode<T> newNode = new DblNode(value);

        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        length++;
    }

    @WorthLooking("traverseToIndex helper for searching/removing/adding")
    private DblNode<T> traverseToIndex(int index) {
        if ((index < 0) || (index > getLength())) {
            throw new RuntimeException(String.format("Bad index: index=%d length=%d", index, length));
        }

        DblNode<T> curr;
        int midPoint = (getLength() / 2);
        if (index > midPoint) { // decrement from tail
            int i = getLength() - 1;
            curr = tail;
            while (i != index) {
                i--;
                curr = curr.getPrev();
            }
        } else {
            // start from head
            int i = 0;
            curr = head;
            while (i != index) {
                i++;
                curr = curr.getNext();
            }
        }

        return curr;
    }

    @Override
    public T insert(T value, int index) {
        if (index > getLength()) {
            append(value);
            return value;
        }

        DblNode<T> prev = traverseToIndex(index - 1);
        DblNode<T> oldNext = prev.getNext();
        DblNode<T> toAdd = new DblNode<>(value);

        toAdd.setNext(oldNext);
        if (oldNext != null) {
            oldNext.setPrev(toAdd);
        }

        toAdd.setPrev(prev);
        prev.setNext(toAdd);

        length++;
        return toAdd.getValue();
    }

    @Override
    public T remove(int index) {
        DblNode<T> remove = traverseToIndex(index);
        DblNode<T> oldPrev = remove.getPrev();
        DblNode<T> oldNext = remove.getNext();

        oldPrev.setNext(oldNext);
        oldNext.setPrev(oldPrev);

        length--;
        return remove.getValue();
    }
}
