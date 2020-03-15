package org.ravi.tlap;

import org.apache.commons.lang3.StringUtils;

//
// p31 pdf=51
public class LuhnValidation {
    int nearest10(int num) {
        int sum = 0;
        while (true) {
            if ((sum + num) % 10 == 0) {
                break;
            }
            sum++;
        }
        return sum;
    }

    int getDigitFromRight(String bigStr, int locFromRight) {
        if (locFromRight <= 0) {
            return -1;
        }

        int begin = bigStr.length() - locFromRight;
        if (begin < 0) {
            return -1;
        }
        String sub = bigStr.substring(begin, begin + 1);
        int value = Integer.parseInt(sub);
        return value;
    }

    /**
     * -1 if error
     */
    int twiceSum(String bigStr, int locFromRight) {
        int value = getDigitFromRight(bigStr, locFromRight);
        if (value < 0) {
            return value;
        }
        value *= 2;
        if (value > 10) {
            return 1 + value % 10;
        }
        return value;
    }

    public boolean validate(int num) {
        int sum = num % 10;
        int bigNum = num / 10;

        return sum == getSum(bigNum);
    }

    public boolean validate(String str) {
        String numStr = StringUtils.substring(str, 0, str.length() - 1);
        String sumStr = StringUtils.substring(str, str.length() - 1);

        int sum = Integer.parseInt(sumStr);
        return sum == getSum(numStr);
    }

    public int getSum(String bigStr) {
        int tmpSum = 0;

        int loc = 1;
        while (true) {
            int twiceSum = twiceSum(bigStr, loc);
            if (twiceSum < 0) {
                break;
            }
            tmpSum += twiceSum;
            loc++;
            int value = getDigitFromRight(bigStr, loc);
            if (value < 0) {
                break;
            }
            tmpSum += getDigitFromRight(bigStr, loc);
            loc++;
        }

        return nearest10(tmpSum);
    }

    public int getSum(int bigNum) {
        return getSum(String.valueOf(bigNum));
    }
}
