package org.ravi.udemy.dsa;

import java.util.function.Consumer;

public interface TheSearchTree<T extends Comparable<T>> {
    TheSearchTree<T> insert(T value);

    boolean lookup(T value);

    boolean remove(T value);

    int getSize();

    void inOrder(Consumer<T> consumer);

    // use pre-order to help easily re-create the tree
    void preOrder(Consumer<T> consumer);

    void postOrder(Consumer<T> consumer);

    void breadthFirst(Consumer<T> consumer);

    void breadthFirstRecursive(Consumer<T> consumer);

    default boolean isValidBST() {
        return false;
    }
}
