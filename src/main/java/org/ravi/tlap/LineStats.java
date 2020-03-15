package org.ravi.tlap;

import lombok.Getter;
import lombok.Setter;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.ArrayList;
import java.util.List;

/**
 * exercise 2.9: Write a program that reads a line of text, counting the number of words, identifying the length of the longest word, the greatest number of vowels in a word, and/or any other statistics you can think of.
 * p54 pdf=74
 */
@Getter
@Setter
public class LineStats {
    //RNTODO
    private int longestLen;
    private String longestWord;
    private int mostVowels;
    private List<String> words = new ArrayList<>();

    // strategy: loop over each char, word-break invoke wordFound() and leave-per-char work here
    //            let wordFound update word-level-stats  <<< allows for extension, separation-of-work ...
    @WorthLooking("handle the looping element, not just-checks, also handle the leftover stuff (sb)")
    // REMEMBER: start from public method, not the reverse. Reverse side spoils the original code. see above.
    public static LineStats processLine(String line) {
        StringBuilder sb = new StringBuilder();
        int numVowels = 0;
        LineStats stats = new LineStats();

        for (char ch : line.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                if (sb.length() > 0) {
                    stats.wordFound(sb.toString(), numVowels);
                    sb.setLength(0); // reset sb
                    numVowels = 0;
                }
                continue;
            }

            sb.append(ch);

            if (isVowel(ch)) {
                numVowels++;
            }
        }

        if (sb.length() > 0) {
            stats.wordFound(sb.toString(), numVowels);
        }

        return stats;
    }

    public static boolean isVowel(char c) {
        char ch = Character.toLowerCase(c);
        return ((ch == 'a')
                || (ch == 'e')
                || (ch == 'i')
                || (ch == 'o')
                || (ch == 'u')
        );
    }

    public int getNumWords() {
        return words.size();
    }

    protected void wordFound(String word, int numVowels) {
        words.add(word);

        if (word.length() > longestLen) {
            longestLen = word.length();
            longestWord = word;
        }
        if (numVowels > mostVowels) {
            mostVowels = numVowels;
        }
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }
}
