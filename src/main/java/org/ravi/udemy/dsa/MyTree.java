package org.ravi.udemy.dsa;

import org.ravi.rutils.sams.dsa2.BinarySearchTree;
import org.ravi.rutils.sams.dsa2.TreeNode;

import java.util.function.Consumer;

public class MyTree<T extends Comparable<T>> implements TheSearchTree<T> {
    private TreeNode<T> root;
    private int size;

    public MyTree() {
        super();
    }

    public MyTree(T value) {
        root = new TreeNode<>(value);
        nodeAdded();
    }

    public void nodeAdded() {
        size++;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> node) {
        if (hasRoot()) {
            throw new IllegalArgumentException("Root already exists");
        }
        this.root = node;
        nodeAdded();
    }

    public boolean hasRoot() {
        return root != null;
    }

    private void recursiveBft(TheQueue<TreeNode<T>> queue, Consumer<T> consumer) {
        if (queue.isEmpty()) {
            return;
        }

        TreeNode<T> currentNode = queue.dequeue();
        if (currentNode.hasLeft()) {
            queue.enqueue(currentNode.getLeft());
        }
        if (currentNode.hasRight()) {
            queue.enqueue(currentNode.getRight());
        }

        consumer.accept(currentNode.getValue());
        recursiveBft(queue, consumer);
    }

    @Override
    public TheSearchTree<T> insert(T value) {
        return null;
    }

    @Override
    public boolean lookup(T value) {
        return false;
    }

    @Override
    public boolean remove(T value) {
        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void inOrder(Consumer<T> consumer) {
        BinarySearchTree.inOrder(root,
                n -> consumer.accept(n.getValue()));
    }

    @Override
    public void preOrder(Consumer<T> consumer) {
        BinarySearchTree.preOrder(root,
                n -> consumer.accept(n.getValue()));
    }

    @Override
    public void postOrder(Consumer<T> consumer) {
        BinarySearchTree.postOrder(root,
                n -> consumer.accept(n.getValue()));
    }

    @Override
    @WorthLooking("iterative bfs using a queue")
    public void breadthFirst(Consumer<T> consumer) {
        if ((root == null) || (consumer == null)) {
            return;
        }

        TreeNode<T> currentNode = root;
        TheQueue<TreeNode<T>> queue = new MyLinkedQueue<>();
        queue.enqueue(currentNode);

        while (!queue.isEmpty()) {
            currentNode = queue.dequeue();

            if (currentNode.hasLeft()) {
                queue.enqueue(currentNode.getLeft());
            }
            if (currentNode.hasRight()) {
                queue.enqueue(currentNode.getRight());
            }

            consumer.accept(currentNode.getValue());
        }
    }

    @Override
    @WorthLooking("recursive bfs using helper & queue")
    public void breadthFirstRecursive(Consumer<T> consumer) {
        if ((root == null) || (consumer == null)) {
            return;
        }

        TheQueue<TreeNode<T>> queue = new MyLinkedQueue<>();
        queue.enqueue(root);

        recursiveBft(queue, consumer);
    }

    @Override
    public boolean isValidBST() {
        if (root == null) {
            return false;
        }

        TheQueue<TreeNode<T>> queue = new MyLinkedQueue<>();
        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currNode = queue.dequeue();

            if (currNode.hasLeft()) {
                int result = currNode.getValue().compareTo(currNode.getLeft().getValue());
                // TODO int result = currNode.compareTo(currNode.getLeft());
                if (result <= 0) { // curr is lessThanOrEqual to left, not allowed
                    return false;
                }
                queue.enqueue(currNode.getLeft());
            }
            if (currNode.hasRight()) {
                int result = currNode.getValue().compareTo(currNode.getRight().getValue());
                if (result >= 0) {
                    return false;
                }
                queue.enqueue(currNode.getRight());
            }
        }

        return true;
    }

    // TODO https://www.youtube.com/watch?v=i_Q0v_Ct5lY
    @WorthLooking("checkBSTRecursive: compare left-node should be min<-->root; right-node root<-->max")
    @SuppressWarnings({"unused"})
    private boolean checkBSTRecursive(TreeNode<T> node, T min, T max) {
        throw new IllegalArgumentException(getClass().getSimpleName() + ": implement me!");
    }
}
