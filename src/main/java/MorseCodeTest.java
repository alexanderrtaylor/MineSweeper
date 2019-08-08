import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MorseCodeTest {

    @Test
    void uniqueMorseRepresentationsTest() {
        assertEquals(2, MorseCode.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        assertEquals(3, MorseCode.uniqueMorseRepresentations(new String[]{"a", "b", "c", "a"}));
        assertEquals(1, MorseCode.uniqueMorseRepresentations(new String[]{"a", "et"})); }
}