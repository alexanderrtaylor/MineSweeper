import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquaresSortedTest {

    @Test
    void sortedSquaresTest() {
        //input [-4,-1,0,3,10]
        assertArrayEquals(new int[]{0,1,9,16,100}, SquaresSorted.sortedSquares(new int[]{-4,-1,0,3,10}));
        //input [-7,-3,2,3,11]
        assertArrayEquals(new int[]{4,9,9,49,121}, SquaresSorted.sortedSquares(new int[]{-7,-3,2,3,11}));
        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1}, SquaresSorted.sortedSquares(new int[]{1, 1, 1, 1, 1, 1}));
        assertArrayEquals(new int[]{1, 4, 9, 16, 25, 36}, SquaresSorted.sortedSquares(new int[]{-6, -5, -4, -3, -2, -1}));
        assertArrayEquals(new int[]{1, 4, 9, 16, 25, 36}, SquaresSorted.sortedSquares(new int[]{-3, -2, -1, 4, 5, 6}));
        assertArrayEquals(new int[]{100000000, 100000000}, SquaresSorted.sortedSquares(new int[]{-10000, 10000}));
    }
}