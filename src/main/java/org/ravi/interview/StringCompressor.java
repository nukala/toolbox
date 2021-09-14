package org.ravi.interview;

import org.apache.commons.lang3.StringUtils;
import org.ravi.udemy.dsa.WorthLooking;

/**
 * To compress strings like so:
 * aaabbbccc = a3b3c3
 * aabbcc = aabbcc
 * aaaaaabbbbbaaaa = a6b5a4
 */
public class StringCompressor {
    private static String doCompress(Character oldChar, int count) {
        StringBuilder sb = new StringBuilder();

        switch (count) {
            case 1:
                sb.append(oldChar);
                break;
            case 2:
                sb.append(oldChar).append(oldChar);
                break;
            default:
                sb.append(oldChar).append(count);
                break;
        }

        return sb.toString();
    }

    /** compress by reducing repeating chars to their number. So aaabbbccc = a3b3c3 and so on */
    @WorthLooking("pay attention to the last character, it should not be dangling")
    public static String compress(String data) {
        if (StringUtils.isEmpty(data)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder(data.length());
        Character oldChar = null;

        int count = 0;
        for (char ch : data.toCharArray()) {
            if (oldChar == null) {
                oldChar = ch;
                count++;
            } else if (oldChar != ch) { // append
                sb.append(doCompress(oldChar, count));
                count = 1;
                oldChar = ch;
            } else {
                count++;
            }
        }

        if (count > 0) {
            sb.append(doCompress(oldChar, count));
        }

        return sb.toString();
    }
}
