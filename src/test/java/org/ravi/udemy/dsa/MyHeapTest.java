package org.ravi.udemy.dsa;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MyHeapTest {

    @Test
    public void pollMinHeapWithFewItems() {
        MyHeap<Integer> minHeap = MyHeap.MinHeap(null);

        minHeap.add(19);
        minHeap.add(109);
        minHeap.add(13);

        assertThat(minHeap.poll()).isEqualTo(13);
        assertThat(minHeap.poll()).isEqualTo(19);
        assertThat(minHeap.poll()).isEqualTo(109);
    }

    @Test
    public void pollMaxHeapWithFewItems() {
        MyHeap<Integer> maxHeap = MyHeap.MaxHeap(null);

        maxHeap.add(13);
        maxHeap.add(109);
        maxHeap.add(19);

        assertThat(maxHeap.poll()).isEqualTo(109);
        assertThat(maxHeap.poll()).isEqualTo(19);
        assertThat(maxHeap.poll()).isEqualTo(13);
    }

    @Test
    public void pollMaxHeapWithClearInBetween() {
        MyHeap<Integer> maxHeap = MyHeap.MaxHeap(null);

        maxHeap.add(13);
        maxHeap.add(19);

        assertThat(maxHeap.poll()).isEqualTo(19);
        assertThat(maxHeap.poll()).isEqualTo(13);
        maxHeap.clear();
        assertThat(maxHeap.poll()).isNull();
    }

    /** simple wrapper with bare-minimum impls */
    private static class StringWrapper {
        private String str;

        public StringWrapper(String str) {
            this.str = str;
        }

        public static Comparator<StringWrapper> comparator() {
            return Comparator.comparing(sw -> sw.str);
        }
    }

    @Test
    public void addMinObjHeapWithNoComparator() {
        MyHeap<StringWrapper> min = MyHeap.MinHeap(null);

        min.add(new StringWrapper("z"));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> min.add(new StringWrapper("a")));
    }

    @Test
    public void pollMaxObjHeapWithComparator() {
        MyHeap<StringWrapper> swMax = MyHeap.MaxHeap(StringWrapper.comparator());

        swMax.add(new StringWrapper("A"));
        swMax.add(new StringWrapper("z"));
        swMax.add(new StringWrapper("o"));

        assertThat(swMax.poll().str)
                .isEqualTo("z");
        assertThat(swMax.poll().str)
                .isEqualTo("o");
        assertThat(swMax.poll().str)
                .isEqualTo("A");
    }
}
