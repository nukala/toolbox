package org.ravi.udemy.dsa;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.ravi.educative.TheTrie;

@Getter
@Setter
public class MyTrie implements TheTrie {
    private boolean completeWord;
    private int numChildren;
    private MyTrie[] children = new MyTrie[Alphabet.NUM_LETTERS];
    private Character character;

    public static MyTrie of(String sentence) {
        MyTrie trie = new MyTrie();
        trie.add(sentence.split("\\s"));
        return trie;
    }

    public void add(String[] words) {
        for (String word : words) {
            add(word);
        }
    }

    public void add(String str) {
        if (str.length() == 0) {
            return;
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
            child.character = firstChar;
            children[index] = child;
            numChildren++;
        }
        if (str.length() == 1) {
            child.completeWord = true;
        }

        child.add(str.substring(1));
    }

    public boolean hasChildren() {
        return numChildren > 0;
    }

    public boolean contains(String str) {
        return getSubTrie(str) != null;
    }

    public MyTrie getSubTrie(String s) {
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
        MyTrie subTrie = getSubTrie(portion);
        return subTrie != null
                && subTrie.hasChildren();
    }

    // properties of the alphabet
    static class Alphabet {
        public static final int NUM_LETTERS = 26;
        private static final char FIRST_CHAR = 'a';
        public static final int FIRST_CHAR_NUM = Character.getNumericValue(FIRST_CHAR);

        private static int getIndex(char ch) {
            return Character.getNumericValue(Character.toLowerCase(ch)) - FIRST_CHAR_NUM;
        }
    }
}
