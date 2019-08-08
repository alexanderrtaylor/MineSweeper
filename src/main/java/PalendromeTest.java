import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalendromeTest {

    @Test
    void isPalindromeTest() {
        assertEquals(true, Palendrome.isPalindrome("A man, a plan, a canal: Panama"));
        assertEquals(true, Palendrome.isPalindrome("A man, a plan, a anal: Panama"));
        assertEquals(false, Palendrome.isPalindrome("race a car"));
        assertEquals(true, Palendrome.isPalindrome(""));
        assertEquals(true, Palendrome.isPalindrome("1001"));
        assertEquals(false, Palendrome.isPalindrome("1002"));
        assertEquals(true, Palendrome.isPalindrome("10101"));
        assertEquals(true, Palendrome.isPalindrome("101++01"));
        assertEquals(true, Palendrome.isPalindrome("101  01  "));
        assertEquals(true, Palendrome.isPalindrome("101\n01"));
        assertThrows(IllegalArgumentException.class, () -> Palendrome.isPalindrome(null));
    }
}