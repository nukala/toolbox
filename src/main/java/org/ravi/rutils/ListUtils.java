package org.ravi.rutils;

import org.ravi.educative.MyLinkList;
import org.ravi.educative.TheLinkedList;
import org.ravi.udemy.dsa.MyDoublyLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

public class ListUtils {

    public static <T> TheLinkedList<T> dblFrom(int num, Supplier<T> supplier) {
        return addFromSupplier(() -> new MyDoublyLinkedList<>(supplier.get()),
                num,
                supplier);
    }

    private static <T> TheLinkedList<T> addFromSupplier(Supplier<TheLinkedList<T>> listSupplier, int num, Supplier<T> nextValueSupplier) {
        TheLinkedList<T> list = listSupplier.get();

        for (int i = 1; i < num; i++) {
            list.append(nextValueSupplier.get());
        }

        return list;
    }

    public static <T> TheLinkedList<T> from(int num, Supplier<T> supplier) {
        return addFromSupplier(() -> new MyLinkList<>(supplier.get()),
                num,
                supplier);
    }

    public static TheLinkedList<Integer> randomInts(int num) {
        Random rand = new Random();
        return from(num, () ->
                rand.nextInt(64));
    }

    public static TheLinkedList<String> from(String sentence) {
        String[] parts = sentence.split(" ");
        return from(parts);
    }

    public static TheLinkedList<String> dblFrom(String sentence) {
        String[] parts = sentence.split(" ");
        return dblFrom(parts);
    }

    @SafeVarargs
    public static <T> TheLinkedList<T> from(T... elems) {
        Iterator<T> iter = Arrays.asList(elems).iterator();

        return from(elems.length,
                iter::next);
    }

    @SafeVarargs
    public static <T> TheLinkedList<T> dblFrom(T... elems) {
        Iterator<T> iter = Arrays.asList(elems)
                .iterator();

        return dblFrom(elems.length,
                iter::next);
    }
}
