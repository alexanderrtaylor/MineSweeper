import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanToIntegerTest {

    @Test
    void romanToIntTest() {
        assertEquals(5, RomanToInteger.romanToInt("V"));
        assertEquals(3, RomanToInteger.romanToInt("III"));
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(55, RomanToInteger.romanToInt("LV"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
        assertEquals(0, RomanToInteger.romanToInt(""));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt(null));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MCGXCIV"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("GCMCIV"));
        assertThrows(IllegalArgumentException.class, () -> RomanToInteger.romanToInt("MCXCIVG"));
    }
}