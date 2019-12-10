package org.ravi.tlap;

import org.ravi.udemy.dsa.WorthLooking;

// special decoding system by reading numbers and flipping around among upper/lower/punctation with modulo 27/27/9
// Chapter2 see p41 or pdf=61
public class DecodeSpecial {
    static char[] PUNCTUATIONS = {'*', '!', '?', ',', '.', ' ', ';', '"', '\''};
    private Mode mode = Mode.UPPER;

    public String decode(String str) {
        String decoded = "";

        for (String part : str.split(",")) {
            int num = Integer.parseInt(part);
            int mod = num % mode.getModulo();
            if (mod == 0) {
                mode = mode.getNext();
                continue;
            }
            char ch = mode.convert(mod);
            decoded += ch;
        }

        return decoded;
    }


    enum Mode {
        @WorthLooking("careful around the starting index. here it is 1")
        UPPER(27) {
            public char convert(int mod) {
                return (char) ('A' + mod - 1);
            }
        },
        LOWER(27) {
            public char convert(int mod) {
                return (char) ('a' + mod - 1);
            }
        },
        PUNCTUATION(9) {
            public char convert(int mod) {
                return PUNCTUATIONS[mod];
            }
        };

        private int modulo;

        Mode(int divisor) {
            modulo = divisor;
        }

        public Mode getNext() {
            Mode curr = this;
            if (curr == UPPER) {
                return LOWER;
            } else if (curr == LOWER) {
                return PUNCTUATION;
            } else if (curr == PUNCTUATION) {
                return UPPER;
            }

            throw new IllegalStateException("curr=" + curr + ", not known");
        }

        public int getModulo() {
            return modulo;
        }

        public abstract char convert(int mod);
    }
}
