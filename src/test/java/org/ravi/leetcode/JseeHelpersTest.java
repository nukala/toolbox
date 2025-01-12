package org.ravi.leetcode;

import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JseeHelpersTest {

    // https://leetcode.com/problems/simplify-path/editorial/
    public static String simplifyPath(String path) {
        // Initialize a deque as a stack
        Deque<String> deque = new ArrayDeque<>();

        String[] components = path.split("/");

        // Split the input string on "/" as the delimiter
        // and process each portion one by one
        for (String directory : components) {
            // A no-op for a "." or an empty string
            if (directory.equals(".") || directory.isEmpty()) {
                continue;
            } else if (directory.equals("..")) {

                if (!deque.isEmpty()) {
                    deque.removeFirst();
                }
            } else {
                deque.addFirst(directory);
            }
        }

        // Stich together all the directory names together
        StringBuilder result = new StringBuilder();
        while (!deque.isEmpty()) {
            result.append("/").append(deque.removeLast());
        }

        return result.length() > 0 ? result.toString() : "/";
    }

    public static String[] doSplit(String str, Optional<String> regexSeparatorOpt) {
        Objects.requireNonNull(str, "string to split cannot be null");

        return regexSeparatorOpt
                .map(str::split)
                .orElseGet(() -> new String[]{str});
    }

    @Test
    public void sbDeleteChar() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("Cablifornia");
        assertThat(sb.toString()).isEqualToIgnoringCase("cablifornia");
        sb.deleteCharAt(2);
        assertThat(sb.toString()).isEqualTo("California");
    }

    @Test
    public void firstKeyTreeMapDoesNotRemove() {
        TreeMap<String, Boolean> map = new TreeMap<>();
        map.put("z", Boolean.TRUE);
        map.put("d", Boolean.TRUE);
        map.put("a", Boolean.TRUE);

        assertThat(map.size()).isEqualTo(3);
        String first = map.firstKey();
        assertThat(first).isEqualTo("a");
        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    public void problem71() {
        assertThat(simplifyPath("/a/b////c/../././d/../f/"))
                .isEqualTo("/a/b/f");

        assertThat(simplifyPath("/.../a/../b/c/../d/./"))
                .isEqualTo("/.../b/d");
    }

    @Test
    public void splitWithRegexSimple() {
        String[] parts = doSplit("There is an UPS truck at the pole", Optional.of("\\s"));

        System.out.println("parts=" + Arrays.toString(parts));
        assertThat(parts).isNotNull();
        assertThat(parts.length).describedAs("number check").isEqualTo(8);
        assertThat(parts).describedAs("array check")
                .containsExactly("There", "is", "an", "UPS", "truck", "at", "the", "pole");
    }


    @Test
    public void splitWithRegexTabsAndNewLines() {
        @WorthLooking("trailing space does not create a new element")
        String str = "There is an   UPS\t\t\t\ttruck at the\n\npole \t \n ";
        String[] parts = doSplit(str, Optional.of("\\s"));

        System.out.println("\"" + str + "\", parts=" + Arrays.toString(parts) + "." + parts.length);
        assertThat(parts).isNotNull();
        assertThat(parts.length).describedAs("number check").isEqualTo(14);
        assertThat(parts).describedAs("array check")
                .containsExactly("There", "is", "an"
                        , "", "",  // three spaces
                        "UPS"
                        , "", "", "", "truck" // 4 tabs
                        , "at", "the",
                        "", // two newlines
                        "pole");
    }
}
