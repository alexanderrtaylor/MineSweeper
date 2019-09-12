import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {

    @Test
    void romanToIntTest() {
        Assertions.assertEquals(5, RomanToInteger.romanToInt("V"));
        Assertions.assertEquals(3, RomanToInteger.romanToInt("III"));
        Assertions.assertEquals(4, RomanToInteger.romanToInt("IV"));
        Assertions.assertEquals(55, RomanToInteger.romanToInt("LV"));
        Assertions.assertEquals(9, RomanToInteger.romanToInt("IX"));
        Assertions.assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        Assertions.assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        Assertions.assertEquals(0, RomanToInteger.romanToInt(""));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt(null));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MCGXCIV"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("GCMCIV"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MCXCIVG"));
    }
}