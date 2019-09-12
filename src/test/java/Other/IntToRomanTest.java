import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntToRomanTest {

    @Test
    void intToRomanTest() {
        Assertions.assertEquals("IX", IntToRoman.intToRoman(9));
        Assertions.assertEquals("LV", IntToRoman.intToRoman(55));
        Assertions.assertEquals("IV", IntToRoman.intToRoman(4));
        Assertions.assertEquals("LVIII", IntToRoman.intToRoman(58));
        Assertions.assertEquals("XLV", IntToRoman.intToRoman(45));
        Assertions.assertEquals("MCMXCIV", IntToRoman.intToRoman(1994));
        Assertions.assertEquals("", IntToRoman.intToRoman(0));
    }
    @Test
    void basicTest() {
        Assertions.assertEquals("VIII", IntToRoman.intToRoman(8));
        Assertions.assertEquals("IX", IntToRoman.intToRoman(9));

        // Party like it's...
        Assertions.assertEquals("MCMXCIX", IntToRoman.intToRoman(1999));
        Assertions.assertEquals("MMCMXCIX", IntToRoman.intToRoman(2999));
    }

    @Test
    void singleNumerals() {
        // Each single numeral
        Assertions.assertEquals("I", IntToRoman.intToRoman(1));
        Assertions.assertEquals("V", IntToRoman.intToRoman(5));
        Assertions.assertEquals("X", IntToRoman.intToRoman(10));
        Assertions.assertEquals("L", IntToRoman.intToRoman(50));
        Assertions.assertEquals("C", IntToRoman.intToRoman(100));
        Assertions.assertEquals("D", IntToRoman.intToRoman(500));
        Assertions.assertEquals("M", IntToRoman.intToRoman(1000));
    }

    @Test
    void multiples() {
        // Multiples
        Assertions.assertEquals("II", IntToRoman.intToRoman(2));
        Assertions.assertEquals("III", IntToRoman.intToRoman(3));
        Assertions.assertEquals("XX", IntToRoman.intToRoman(20));
        Assertions.assertEquals("XXX", IntToRoman.intToRoman(30));
        Assertions.assertEquals("CC", IntToRoman.intToRoman(200));
        Assertions.assertEquals("CCC", IntToRoman.intToRoman(300));
        Assertions.assertEquals("MM", IntToRoman.intToRoman(2000));
        Assertions.assertEquals("MMM", IntToRoman.intToRoman(3000));
    }

    @Test
    void longestRepeatingNumerals() {
        Assertions.assertEquals("DCCCLXXXVIII", IntToRoman.intToRoman(888));
        Assertions.assertEquals("MMMDCCCLXXXVIII", IntToRoman.intToRoman(3888));
    }

    @Test
    void subtractives() {
        // Each subtractive alone
        Assertions.assertEquals("IV", IntToRoman.intToRoman(4));
        Assertions.assertEquals("XL", IntToRoman.intToRoman(40));
        Assertions.assertEquals("CD", IntToRoman.intToRoman(400));

        Assertions.assertEquals("IX", IntToRoman.intToRoman(9));
        Assertions.assertEquals("XC", IntToRoman.intToRoman(90));
        Assertions.assertEquals("CM", IntToRoman.intToRoman(900));

        // Together
        Assertions.assertEquals("CDXLIV", IntToRoman.intToRoman(444));
        Assertions.assertEquals("CMXCIX", IntToRoman.intToRoman(999));

    }
}