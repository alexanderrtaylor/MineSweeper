import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MorseCodeTest {

    @Test
    void uniqueMorseRepresentationsTest() {
        Assertions.assertEquals(2, MorseCode.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        Assertions.assertEquals(3, MorseCode.uniqueMorseRepresentations(new String[]{"a", "b", "c", "a"}));
        Assertions.assertEquals(1, MorseCode.uniqueMorseRepresentations(new String[]{"a", "et"})); }
}