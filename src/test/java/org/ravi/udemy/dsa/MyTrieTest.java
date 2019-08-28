package org.ravi.udemy.dsa;

import org.junit.Test;
import org.ravi.educative.TheTrie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.udemy.dsa.MyTrie.of;

public class MyTrieTest {
    @Test
    public void useAdd() {
        TheTrie trie = new MyTrie();
        trie.add(new String[]{"park", "parker", "parked", "parking", "peter", "car"});

        assertThat(trie.getNumChildren())
                .isEqualTo(2);
    }

    @Test
    public void useOf() {
        TheTrie trie = of("car was here where");

        assertThat(trie.getNumChildren())
                .isEqualTo(3);
    }

    @Test
    public void nodeTest() {
        TheTrie trie = of("park parker parked parking Peter");

        assertThat(trie.getNumChildren())
                .isEqualTo(1); // all of 'em start with 'p'
        assertThat(trie.getSubTrie("was"))
                .isNull();

        TheTrie park = trie.getSubTrie("park");
        assertThat(park).isNotNull();
        assertThat(park.getNumChildren())
                .isEqualTo(2);

        assertThat(trie.getSubTrie("pETe").hasChildren())
                .isTrue();
        assertThat(trie.getSubTrie("pEtER").hasChildren())
                .isFalse();
    }

    @Test
    public void containsTest() {
        TheTrie trie = of("peter parker presented parking permit");

        assertThat(trie.getSubTrie("p").getNumChildren()).isEqualTo(3);
        assertThat(trie.contains("pres")).isTrue();
        assertThat(trie.contains("was")).isFalse();

        TheTrie park = trie.getSubTrie("park");
        assertThat(park.hasChildren()).isTrue();
        assertThat(park.getNumChildren()).isEqualTo(2);
        assertThat(park.contains("er")).isTrue();
        assertThat(park.contains("parking")).isFalse();

        assertThat(trie.isPrefix("park")).isTrue();
        assertThat(trie.contains("was")).isFalse();
        assertThat(trie.contains("")).isFalse();
        assertThat(trie.isPrefix("peter")).isFalse();
        assertThat(trie.contains("pete")).isTrue();
    }
}
