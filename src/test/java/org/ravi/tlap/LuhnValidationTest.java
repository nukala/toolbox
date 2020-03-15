package org.ravi.tlap;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuhnValidationTest {
    LuhnValidation luhn = new LuhnValidation();

    @Test
    public void nearestTests() {
        assertThat(luhn.nearest10(27)).isEqualTo(3);

        assertThat(luhn.nearest10(1)).isEqualTo(9);
        assertThat(luhn.nearest10(0)).isEqualTo(0);
    }

    @Test
    public void twiceSumTest() {
        assertThat(luhn.twiceSum("1234", 1)).isEqualTo(8);

        assertThat(luhn.twiceSum("786", 1)).isEqualTo(3);
        assertThat(luhn.twiceSum("786", 2)).isEqualTo(7);
        assertThat(luhn.twiceSum("786", 3)).isEqualTo(5);

        assertThat(luhn.twiceSum("786", 4)).isEqualTo(-1);
        assertThat(luhn.twiceSum("786", 0)).isEqualTo(-1);
        assertThat(luhn.twiceSum("786", -3)).isEqualTo(-1);
    }

    @Test
    public void bookSample() {
        int id = 176248;
        assertThat(luhn.getSum(id)).isEqualTo(3);

        int withSum = id * 10 + 3;
        assertThat(luhn.validate(withSum)).isTrue();
    }

    @Test
    public void wikipediaSample() {
        String idStr = "7992739871";

        assertThat(luhn.getSum(idStr)).isEqualTo(3);
        assertThat(luhn.validate(idStr + "3")).isTrue();
    }

    @Ignore("Full of meaningless values?")
    public void simcardFromWikipedia() {
        //assertThat(luhn.getSum("896101950123440000")).isEqualTo(1);
        assertThat(luhn.getSum("950123440000")).isEqualTo(8);

        assertThat(luhn.validate("9501234400008")).isTrue();
        assertThat(luhn.validate("8961019501234400001")).isTrue();
    }
}
