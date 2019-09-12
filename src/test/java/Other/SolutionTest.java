import org.junit.jupiter.api.*;

public class SolutionTest {

    @Test
    public void numJewelsInStonesTest() {
        Assertions.assertEquals(3, Solution.numJewelsInStones("aA", "aAAbbbb"));
        Assertions.assertEquals(0, Solution.numJewelsInStones("aA", "125Ils\\"));
        Assertions.assertEquals(3, Solution.numJewelsInStones("aA", "125Ils\naAAbbbb"));
        Assertions.assertEquals(0, Solution.numJewelsInStones("aA", ""));
        Assertions.assertEquals(0, Solution.numJewelsInStones("", "aAAbbbb"));
        Assertions.assertEquals(0, Solution.numJewelsInStones(null, "aAAbbbb"));
        Assertions.assertEquals(0, Solution.numJewelsInStones("aA", null));

    }
}