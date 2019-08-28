package org.ravi.rutils.sams.dsa2;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.Consumer;

// https://algorithmsandme.com/level-order-traversal-of-binary-tree/
public class BinarySearchTree<T> {
    private TreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    @WorthLooking("DFS: depth first search - left->root->right")
    public static <T> void inOrder(TreeNode<T> node, Consumer<TreeNode<T>> consumer) {
        if ((node == null) || (consumer == null)) {
            return;
        }
        if (node.hasLeft()) {
            inOrder(node.getLeft(), consumer);
        }
        consumer.accept(node);
        if (node.hasRight()) {
            inOrder(node.getRight(), consumer);
        }
    }

    @WorthLooking("DFS: depth first search - root->left->right")
    public static <T> void preOrder(TreeNode<T> node, Consumer<TreeNode<T>> consumer) {
        if ((node == null) || (consumer == null)) {
            return;
        }
        consumer.accept(node);
        if (node.hasLeft()) {
            preOrder(node.getLeft(), consumer);
        }
        if (node.hasRight()) {
            preOrder(node.getRight(), consumer);
        }
    }

    @WorthLooking("DFS: depth first search -  left->right->root")
    public static <T> void postOrder(TreeNode<T> node, Consumer<TreeNode<T>> consumer) {
        if ((node == null) || (consumer == null)) {
            return;
        }
        if (node.hasLeft()) {
            postOrder(node.getLeft(), consumer);
        }
        if (node.hasRight()) {
            postOrder(node.getRight(), consumer);
        }
        consumer.accept(node);
    }

    public void insert(int value) {
        this.root = insertNode(this.root, value);
    }

    private TreeNode insertNode(TreeNode root, int value) {
        if (root == null) {
            //if this node is root of tree
            setRoot(new TreeNode<>(value));
        } else {
            if ((int) root.getValue() > value) {
             /* If root is greater than value,
                node should be added to left subtree */
                root.setLeft(insertNode(root.getLeft(), value));
            } else {
                /* If root is less than value,
                node should be added to right subtree */
                root.setRight(insertNode(root.getRight(), value));
            }
        }
        return root;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    public void setRoot(TreeNode node) {
        this.root = node;
    }

    @WorthLooking("Height of binaryTree recursive-max-plus-one")
    public int height() {
        return height(this.getRoot());
    }

    private int height(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftHeight = height(currentNode.getLeft());
        int rightHeight = height(currentNode.getRight());

        return Integer.max(leftHeight, rightHeight) + 1;
    }

    // TODO: Convert into a unit-test
    public static class App {
        public static void main(String args[]) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();

            bst.insert(10);
            bst.insert(5);
            bst.insert(14);
            bst.insert(1);
            bst.insert(6);
            bst.insert(12);
            bst.insert(15);

            System.out.printf("IN-ORDER: ");
            bst.inOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
            System.out.printf("%n");

            System.out.printf("PRE-ORDER: ");
            bst.preOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
            System.out.printf("%n");

            System.out.printf("POST-ORDER: ");
            bst.postOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
            System.out.printf("%n");

            System.out.printf("Height=%d %n", bst.height());
        }
    }
}
