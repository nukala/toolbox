package org.ravi.udemy.dsa;

import java.util.Comparator;

// to compare version numbers such as
//  15 > 14; 15.0.1 < 15.0.2 and 15.0 < 15.0.0.1 and so on
// see p11 of TOP-30-Java-Coding-Tasks in read.amazon.com
public class VersionNumbers implements Comparator<String> {
    private static VersionNumbers INSTANCE = new VersionNumbers();

    private int calculateVersion(String[] versions, int index) {
        if (index < versions.length) {
            return Integer.parseInt(versions[index]);
        }

        return 0;
    }

    public static int check(String leftVersion, String rightVersion) {
        return INSTANCE.compare(leftVersion, rightVersion);
    }

    @Override
    public int compare(String leftVersion, String rightVersion) {
        // if both are empty -- return 0; etc. skipped for brevity

        String[] leftElems = leftVersion.split("\\.");
        String[] rightElems = rightVersion.split("\\.");
        int maxLen = Math.max(leftElems.length, rightElems.length), i = 0;

        while (i < maxLen) {
            int leftNum = calculateVersion(leftElems, i);
            int rightNum = calculateVersion(rightElems, i);

            if (leftNum < rightNum) {
                return -1;
            } else if (leftNum > rightNum) {
                return 1;
            }

            i++;
        }

        return 0;
    }

}
