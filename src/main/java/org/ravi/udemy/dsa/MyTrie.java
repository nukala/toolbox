package org.ravi.udemy.dsa;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.ravi.educative.TheTrie;

import java.util.StringJoiner;

// inspired by: 
//   https://www.toptal.com/java/the-trie-a-neglected-data-structure
//   see also: https://medium.com/basecs/trying-to-understand-tries-3ec6bede0014
// also see = https://medium.com/@amogh.avadhani/how-to-build-a-trie-tree-in-java-9d144aaa0d01
//    (more efficient container for children)
@Getter
@Setter
public class MyTrie implements TheTrie {
    private boolean completeWord;
    private int numChildren;
    // TODO: use a wrapper class to abstract out this!
    private MyTrie[] children = new MyTrie[Alphabet.NUM_LETTERS];

    public static MyTrie of(String sentence) {
        MyTrie trie = new MyTrie();
        trie.add(sentence.split("\\s+"));
        return trie;
    }

    public TheTrie add(String[] words) {
        for (String word : words) {
            add(word);
        }
        return this;
    }

    @WorthLooking("recursive add -- pay attention to target of recursion use child.add ...")
    public TheTrie add(String str) {
        if (str == null || str.isEmpty()) {
            return this;
        }
        char firstChar = str.charAt(0);
        int index = Alphabet.getIndex(firstChar);
        if ((index < 0) || (index > children.length)) {
            String msg = "index generation failed ch=" + firstChar + ", index=" + index;
            throw new IllegalArgumentException(msg);
        }

        MyTrie child = children[index];
        if (child == null) {
            child = new MyTrie();
            children[index] = child;
            numChildren++;
        }
        if (str.length() == 1) {
            child.completeWord = true;
        }

        child.add(str.substring(1));
        return this;
    }

    public boolean hasChildren() {
        return numChildren > 0;
    }

    public boolean contains(String str) {
        return getSubTrie(str) != null;
    }

    @WorthLooking("O(string-length) loop for getting subTrie")
    public TheTrie getSubTrie(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        MyTrie subTrie = this;
        for (char ch : s.toCharArray()) {
            int index = Alphabet.getIndex(ch);
            MyTrie child = subTrie.children[index];
            if (child == null) {
                return null;
            }

            subTrie = child;
        }
        return subTrie;
    }

    public boolean isPrefix(String portion) {
        TheTrie subTrie = getSubTrie(portion);
        return subTrie != null
                && subTrie.hasChildren();
    }

    /* TODO: toString needs some thought
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ");

        if (numChildren > 0) {
            for (int i = 0; i < Alphabet.NUM_LETTERS; i++) {
                if (children[i] != null) {
                    joiner.add(Alphabet.fromIndex(i) + "");
                }
            }
        }
        joiner.add("#" + numChildren);
        return joiner.toString();
    }
     */

    /**
     * properties of the alphabet
     * <br/>
     * make changes here if we have more letters or different Locale (pay attention to getIndex)
     */
    static class Alphabet {
        public static final int NUM_LETTERS = 26;
        private static final char FIRST_CHAR = 'a';
        public static final int FIRST_CHAR_NUM = Character.getNumericValue(FIRST_CHAR);

        public static int getIndex(char ch) {
            return Character.getNumericValue(Character.toLowerCase(ch)) - FIRST_CHAR_NUM;
        }
        public static char fromIndex(int index) {
            char fromDigit = Character.forDigit(index, Character.MAX_RADIX);
            char ch = (char) (index + FIRST_CHAR);

            return ch;
        }
    }
}
