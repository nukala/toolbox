package org.ravi.udemy.dsa;

import org.ravi.rutils.sams.dsa2.TreeNode;

import java.util.Arrays;
import java.util.List;

// https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12366868
//
// corrections performed after video : renamed tmp into currentNode; removed else block after breaks
public class MyBinarySearchTree<T extends Comparable<T>> extends MyTree<T> implements TheSearchTree<T> {
    public static <R extends Comparable<R>> TheSearchTree<R> from(R[] array) {
        return from(Arrays.asList(array));
    }

    public static <R extends Comparable<R>> TheSearchTree<R> from(List<R> list) {
        TheSearchTree<R> tree = new MyBinarySearchTree<>();

        list.forEach(tree::insert);
        return tree;
    }

    @Override
    public MyBinarySearchTree<T> insert(T value) {
        if (value == null) {
            throw new IllegalStateException("Cannot insert null");
        }
        TreeNode<T> newNode = new TreeNode<>(value);
        if (!hasRoot()) {
            setRoot(newNode);
            return this;
        }

        TreeNode<T> currentNode = getRoot();
        while (true) {
            int result = value.compareTo(currentNode.getValue());
            if (result == 0) {
                throw new IllegalStateException(String.format("Cannot insert-same value [%s]", currentNode.getValue()));
            } else if (result > 0) { // go right
                if (!currentNode.hasRight()) { // insert here
                    currentNode.setRight(newNode);
                    break;
                }
                currentNode = currentNode.getRight();
            } else { // go left
                if (!currentNode.hasLeft()) { // insert here
                    currentNode.setLeft(newNode);
                    break;
                }
                currentNode = currentNode.getLeft();
            }
        }

        nodeAdded();
        return this;
    }

    @Override
    public boolean lookup(T value) {
        if (value == null) {
            return false;
        }

        int numCmps = 0;
        boolean found = false;
        TreeNode<T> currentNode = getRoot();
        while (currentNode != null) {
            int result = value.compareTo(currentNode.getValue());
            numCmps++;
            if (result == 0) {
                found = true;
                break;
            } else if (result > 0) { // GREATER: go right
                currentNode = currentNode.getRight();
            } else { // LESSER: go left
                currentNode = currentNode.getLeft();
            }
        }

        System.out.printf("%s: found=%b numCmps = %d %n", value.toString(), found, numCmps);
        return found;
    }

//    private Optional<TreeNode<T>> traverse(TreeNode<T> start, T value) {
//        int numCmps = 0;
//        Optional<TreeNode<T>> found = Optional.empty();
//
//        TreeNode<T> currentNode = start;
//        while (currentNode != null) {
//            int result = value.compareTo(currentNode.getValue());
//            numCmps++;
//            if (result == 0) {
//                found = Optional.of(currentNode);
//                break;
//            } else if (result > 0) { // go right
//                currentNode = currentNode.getRight();
//            } else {
//                currentNode = currentNode.getLeft();
//            }
//        }
//
//        System.out.printf("%s: found=%b numCmps = %d %n", value.toString(), found.isPresent(), numCmps);
//        return found;
//    }

    @Override
    // TODO https://www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12367214
    // TODO https://repl.it/@aneagoie/Data-Structures-Trees
    // TODO http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/BST-delete.html
    public boolean remove(T value) {
        if ((value == null) || (!hasRoot())) {
            return false;
        }

        TreeNode<T> parent = null;
        TreeNode<T> toRemove = getRoot();

        while (toRemove != null) {
            int result = value.compareTo(toRemove.getValue());

            if (result == 0) {
                break;
            } else if (result > 0) { // GREATER: go right
                parent = toRemove;
                toRemove = toRemove.getRight();
            } else { // LESSER: go left
                parent = toRemove;
                toRemove = toRemove.getLeft();
            }
        }

        if (toRemove == null) {
            return false;
        }

        if (toRemove != null) {
        }
        throw new IllegalArgumentException("TODO To be completed");
        //return false;
    }
}
