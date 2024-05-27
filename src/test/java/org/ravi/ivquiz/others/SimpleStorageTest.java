package org.ravi.ivquiz.others;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/** Use Junit tests instead of hard-coded main method */
public class SimpleStorageTest {
    private SimpleStorage ss;

    @Before
    public void beforeEveryTest() {
        ss = new SimpleStorage();
    }
    @Test
    public void levelOne() {
        assertThat(ss.addFile("/file_1")).isTrue();
        assertThat(ss.addFile("/file_1")).isFalse();
        assertThat(ss.addFile("/file_2")).isTrue();
        assertThat(ss.addFile("/dir_1/file_2")).isTrue();
        assertThat(ss.deleteFile("/file_2")).isTrue();
        assertThat(ss.deleteFile("/file_2")).isFalse();
    }

    @Test
    public void levelTwo() {
        levelOne();

        assertThat(ss.copyFile("/dir_1/file_2", "/")).isTrue();
        assertThat(ss.copyFile("/file_2", "/")).isFalse();
        assertThat(ss.copyFile("/file_3", "/dir_1/")).isFalse();
        assertThat(ss.copyFile("/file_3", "/dir_3")).isFalse();
    }

}
