package org.ravi.tlap;

// special decoding system by reading numbers and flipping around among upper/lower/punctation with modulo 27/27/9
// Chapter2 see p41 or pdf=61
public class DecodeSpecial {
    static char[] PUNCTUATIONS = {'*', '!', '?', ',', '.', ' ', ';', '"', '\''};
    private Mode mode = Mode.UPPER;

    private char convert(int mod) {
        if (mode == Mode.UPPER) {
            return (char) ('A' + mod - 1);
        } else if (mode == Mode.LOWER) {
            return (char) ('a' + mod - 1);
        } else if (mode == Mode.PUNCTUATION) {
            return PUNCTUATIONS[mod];
        }
        throw new IllegalStateException("mod=" + mod + ", mode=" + mode);
    }

    public String decode(String str) {
        String decoded = "";

        for (String part : str.split(",")) {
            int num = Integer.parseInt(part);
            int mod = num % mode.getModulo();
            if (mod == 0) {
                mode = mode.getNext();
                continue;
            }
            char ch = convert(mod);

            decoded += ch;
        }

        return decoded;
    }


    enum Mode {
        UPPER(27),
        LOWER(27),
        PUNCTUATION(9);

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
    }
}
