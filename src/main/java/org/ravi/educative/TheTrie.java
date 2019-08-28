package org.ravi.educative;

public interface TheTrie {
    void add(String word);

    boolean contains(String word);

    /**
     * True iff the specified string is a prefix. That is there are more letters possible after the specified value.
     * <br/>
     * For a trie containing: cat ant catch catcher&nbsp;&nbsp;&nbsp;This method will be true for:
     * <ul>
     *     <li>cat</li>
     *     <li>catch</li>
     * </ul>
     * False for:
     * <ul>
     *     <li>ant</li>
     *     <li>catcher</li>
     * </ul>
     *
     * @param word
     * @return
     */
    boolean isPrefix(String word);

    void add(String[] words);

    boolean hasChildren();

    TheTrie getSubTrie(String part);

    int getNumChildren();
}
