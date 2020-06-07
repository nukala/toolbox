package org.ravi.rutils.sams.dsa2;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.function.Consumer;

// https://algorithmsandme.com/level-order-traversal-of-binary-tree/
// TODO: I have serious problems with the way @see #insert is written up. Not sure if there is any code-coverage
@SuppressWarnings({"unchecked", "WeakerAccess"}) // too many violators
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

    // TODO: make sense of this, DOES NOT WORK@2
//    public void insert(int value) {
//        insertNode(this.root, value);
//    }
//
//    private TreeNode<T> insertNode(TreeNode root, int value) {
//        if (root == null) {
//            //if this node is root of tree
//            setRoot(new TreeNode(value));
//        } else {
//            if ((int) root.getValue() > value) {
//             /* If root is greater than value,
//                node should be added to left subtree */
//                root.setLeft(insertNode(root.getLeft(), value));
//            } else {
//                /* If root is less than value,
//                node should be added to right subtree */
//                root.setRight(insertNode(root.getRight(), value));
//            }
//        }
//        return root;
//    }

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(TreeNode<T> node) {
        this.root = node;
    }

    @WorthLooking("Height of binaryTree recursive-max-plus-one")
    public int height() {
        return height(this.getRoot());
    }

    private int height(TreeNode<T> currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftHeight = height(currentNode.getLeft());
        int rightHeight = height(currentNode.getRight());

        return Integer.max(leftHeight, rightHeight) + 1;
    }

    // TODO: Convert into a unit-test, DOES NOT WORK@2
//    public static class App {
//        public static void main(String[] args) {
//            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
//
//            bst.insert(10);
//            bst.insert(5);
//            bst.insert(14);
//            bst.insert(1);
//            bst.insert(6);
//            bst.insert(12);
//            bst.insert(15);
//
//            System.out.print("IN-ORDER: ");
//            inOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
//            System.out.printf("%n");
//
//            System.out.print("PRE-ORDER: ");
//            preOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
//            System.out.printf("%n");
//
//            System.out.print("POST-ORDER: ");
//            postOrder(bst.getRoot(), n -> System.out.printf("%s ", n.getValue()));
//            System.out.printf("%n");
//
//            System.out.printf("Height=%d %n", bst.height());
//        }
//    }
}
