package org.ravi.tlap;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ravi.tlap.LuhnValidation.nearest10;
import static org.ravi.tlap.LuhnValidation.twiceSum;

public class LuhnValidationTest {
    @Test
    public void nearestTests() {
        assertThat(nearest10(27)).isEqualTo(3);

        assertThat(nearest10(1)).isEqualTo(9);
        assertThat(nearest10(0)).isEqualTo(0);
    }

    @Test
    public void twiceSumTest() {
        assertThat(twiceSum("1234", 1)).isEqualTo(8);

        assertThat(twiceSum("786", 1)).isEqualTo(3);
        assertThat(twiceSum("786", 2)).isEqualTo(7);
        assertThat(twiceSum("786", 3)).isEqualTo(5);

        assertThat(twiceSum("786", 4)).isEqualTo(-1);
        assertThat(twiceSum("786", 0)).isEqualTo(-1);
        assertThat(twiceSum("786", -3)).isEqualTo(-1);
    }

    @Test
    public void roundTrip() {
        LuhnValidation validation = new LuhnValidation();
        int id = 176248;
        assertThat(validation.getSum(id)).isEqualTo(3);

        int withSum = id * 10 + 3;
        assertThat(validation.validate(withSum)).isTrue();
    }

}
