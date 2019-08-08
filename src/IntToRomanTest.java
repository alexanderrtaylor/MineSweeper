import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.*;

class IntToRomanTest {

    @Test
    void intToRomanTest() {
        assertEquals("IX", IntToRoman.intToRoman(9));
        assertEquals("LV", IntToRoman.intToRoman(55));
        assertEquals("IV", IntToRoman.intToRoman(4));
        assertEquals("LVIII", IntToRoman.intToRoman(58));
        assertEquals("XLV", IntToRoman.intToRoman(45));
        assertEquals("MCMXCIV", IntToRoman.intToRoman(1994));
        assertEquals("", IntToRoman.intToRoman(0));
    }
    @Test
    void basicTest() {
        assertEquals("VIII", IntToRoman.intToRoman(8));
        assertEquals("IX", IntToRoman.intToRoman(9));

        // Party like it's...
        assertEquals("MCMXCIX", IntToRoman.intToRoman(1999));
        assertEquals("MMCMXCIX", IntToRoman.intToRoman(2999));
    }

    @Test
    void singleNumerals() {
        // Each single numeral
        assertEquals("I", IntToRoman.intToRoman(1));
        assertEquals("V", IntToRoman.intToRoman(5));
        assertEquals("X", IntToRoman.intToRoman(10));
        assertEquals("L", IntToRoman.intToRoman(50));
        assertEquals("C", IntToRoman.intToRoman(100));
        assertEquals("D", IntToRoman.intToRoman(500));
        assertEquals("M", IntToRoman.intToRoman(1000));
    }

    @Test
    void multiples() {
        // Multiples
        assertEquals("II", IntToRoman.intToRoman(2));
        assertEquals("III", IntToRoman.intToRoman(3));
        assertEquals("XX", IntToRoman.intToRoman(20));
        assertEquals("XXX", IntToRoman.intToRoman(30));
        assertEquals("CC", IntToRoman.intToRoman(200));
        assertEquals("CCC", IntToRoman.intToRoman(300));
        assertEquals("MM", IntToRoman.intToRoman(2000));
        assertEquals("MMM", IntToRoman.intToRoman(3000));
    }

    @Test
    void longestRepeatingNumerals() {
        assertEquals("DCCCLXXXVIII", IntToRoman.intToRoman(888));
        assertEquals("MMMDCCCLXXXVIII", IntToRoman.intToRoman(3888));
    }

    @Test
    void subtractives() {
        // Each subtractive alone
        assertEquals("IV", IntToRoman.intToRoman(4));
        assertEquals("XL", IntToRoman.intToRoman(40));
        assertEquals("CD", IntToRoman.intToRoman(400));

        assertEquals("IX", IntToRoman.intToRoman(9));
        assertEquals("XC", IntToRoman.intToRoman(90));
        assertEquals("CM", IntToRoman.intToRoman(900));

        // Together
        assertEquals("CDXLIV", IntToRoman.intToRoman(444));
        assertEquals("CMXCIX", IntToRoman.intToRoman(999));

    }
}