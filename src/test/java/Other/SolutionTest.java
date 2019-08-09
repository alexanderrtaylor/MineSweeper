import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    @Test
    public void numJewelsInStonesTest() {
        assertEquals(3, Solution.numJewelsInStones("aA", "aAAbbbb"));
        assertEquals(0, Solution.numJewelsInStones("aA", "125Ils\\"));
        assertEquals(3, Solution.numJewelsInStones("aA", "125Ils\naAAbbbb"));
        assertEquals(0, Solution.numJewelsInStones("aA", ""));
        assertEquals(0, Solution.numJewelsInStones("", "aAAbbbb"));
        assertEquals(0, Solution.numJewelsInStones(null, "aAAbbbb"));
        assertEquals(0, Solution.numJewelsInStones("aA", null));

    }
}