package org.ravi.rutils.sams.dsa2;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

// https://algorithmsandme.com/level-order-traversal-of-binary-tree/
@Getter
@Setter
//T extends Comparable<? super T>
public class TreeNode<T> {
    private T value;
    private Optional<TreeNode<T>> left;
    private Optional<TreeNode<T>> right;

    public TreeNode(T value) {
        this.value = value;
        this.left = Optional.empty();
        this.right = Optional.empty();
    }

    public TreeNode<T> getRight() {
        return right.orElse(null);
    }

    public void setRight(TreeNode<T> node) {
        this.right = Optional.of(node);
    }

    public void setRight(T value) {
        this.right = Optional.of(new TreeNode<>(value));
    }

    public TreeNode<T> getLeft() {
        return left.orElse(null);
    }

    public void setLeft(TreeNode<T> node) {
        this.left = Optional.of(node);
    }

    public void setLeft(T value) {
        this.left = Optional.of(new TreeNode<>(value));
    }

    public boolean hasLeft() {
        return left.isPresent();
    }

    public boolean hasRight() {
        return right.isPresent();
    }

    // TODO
//    public int compareTo(Object obj) {
//        TreeNode<T> other = (TreeNode<T>)obj;
//
//        //currNode.getValue().compareTo(currNode.getLeft().getValue());
//        return getValue().compareTo(other.getValue());
//    }
}

