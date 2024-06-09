package org.ravi.udemy.dsa;

import org.junit.Ignore;
import org.junit.Test;
import org.ravi.rutils.sams.dsa2.TreeNode;

import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BinarySearchTreeTest {
    private TheSearchTree<Integer> tree = MyBinarySearchTree.from(new Integer[]{9, 4, 20, 170, 6, 15, 1});

    @Test
    public void insertAndVerifyInOrder() {
        StringJoiner joiner = new StringJoiner(" ");
        tree.inOrder(n -> joiner.add(n.toString()));
        System.out.printf("INORDER = [%s] %n", joiner);
        assertThat(joiner.toString())
                .as("inorder toString")
                .contains("1 4 6 9 15 20 170");
    }

    @Test
    public void lookupTest() {
        tree.insert(30);

        assertThat(tree.lookup(20))
                .isTrue();
        assertThat(tree.lookup(9))
                .isTrue();
        assertThat(tree.lookup(33))
                .as("33 does not exist")
                .isFalse();
    }

    @Test
    public void insertAndLookupNull() {
        tree = MyBinarySearchTree.from(new Integer[]{9, 4, 6});
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> tree.insert(null))
                .withNoCause();

        assertThat(tree.lookup(null))
                .isFalse();
    }

    @Test
    public void lookupStrings() {
        TheSearchTree<String> tree = MyBinarySearchTree.from("City of Fremont is in California. We are the 31st state".split(" "));
        assertThat(tree.lookup("Fremont")).isTrue();
        assertThat(tree.lookup("California.")).isTrue();
        assertThat(tree.lookup("Tyngsboro")).isFalse();
        assertThat(tree.getSize()).isEqualTo(11);
        StringJoiner joiner = new StringJoiner(" ");
        tree.inOrder(joiner::add);
        System.out.printf("tree = [%s]%n", joiner);
    }

    @Test
    public void breadthFirstCheck() {
        StringJoiner joiner = new StringJoiner(" ");
        tree.breadthFirst((Integer ii) -> joiner.add(ii.toString()));
        System.out.printf("%s %n", joiner);
        assertThat(joiner.toString())
                .as("bfsConsumption string verification")
                .isEqualTo("9 4 20 1 6 15 170");
    }

    @Test
    public void checkRecursiveBfs() {
        StringJoiner joiner = new StringJoiner(", ");
        tree.breadthFirstRecursive(i -> joiner.add(i.toString()));
//        System.out.printf("%s %n", joiner.toString());
        assertThat(joiner.toString())
                .as("bfsConsumption string verification")
                .isEqualTo("9, 4, 20, 1, 6, 15, 170");
    }

    @Test
    public void preOrderCheck() {
        StringJoiner joiner = new StringJoiner(",");
        tree.preOrder((Integer ii) -> joiner.add(ii.toString()));
        assertThat(joiner.toString())
                .isEqualTo("9,4,1,6,20,15,170");
    }

    @Test
    public void checkPostOrder() {
        StringJoiner joiner = new StringJoiner(" ");
        tree.postOrder((Integer ii) -> joiner.add(ii.toString()));
        assertThat(joiner.toString())
                .isEqualTo("1 6 4 15 170 20 9");
    }

    @Test
    public void validityTest() {
        assertThat(tree.isValidBST())
                .isEqualTo(true);
    }

    @Test
    public void invalidBst() {
        //    5
        //   / \
        //  1   4
        //     / \
        //    3   6
        MyTree<Integer> tree = new MyTree<>(5);

        tree.getRoot().setLeft(1);
        TreeNode<Integer> four = new TreeNode<>(4);
        tree.getRoot().setRight(four);
        four.setLeft(3);
        four.setRight(6);

        StringJoiner joiner = new StringJoiner(" ");
        tree.preOrder(ii -> joiner.add(ii.toString()));
        System.out.printf("Verify tree structure by gazing at pre-order !!! %n%s %n", joiner);
        // use pre-order to help easily re-create the tree
        assertThat(joiner.toString())
                .isEqualTo("5 1 4 3 6");
        assertThat(tree.isValidBST())
                .isFalse();
    }

    @Ignore("TODO: remove not implemented yet, hence disabled")
    public void removeTest() {
        assertThat(tree.getSize()).isEqualTo(7);
        assertThat(tree.lookup(20)).isEqualTo(true);

        assertThat(tree.remove(20)).isTrue();
        assertThat(tree.remove(20)).isFalse();
        assertThat(tree.lookup(20)).isFalse();
    }
}
