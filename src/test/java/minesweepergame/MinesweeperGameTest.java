package minesweepergame;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperGameTest {

    @Test
    void startNewGameBasic() throws Exception {
        MinesweeperGame testMinesweeper = new MinesweeperGame();
        testMinesweeper.startNewGame(10 ,10);
        Board.CellType[][] testBoard = testMinesweeper.getBoard();
        assertFalse(testBoard == null);
        assertEquals(10, testBoard.length);
        assertEquals(10, testBoard[0].length);
        assertEquals(1, testMinesweeper.getTotalMineCount());

    }


    @Test
    void updateBoard() {
    }

    @Test
    void clickTest() throws Exception {
        MinesweeperGame testMinesweeper = new MinesweeperGame();
        testMinesweeper.startNewGame(10 ,10);
        Point testPoint = new Point(1, 1);
        testMinesweeper.click(testPoint, MinesweeperGame.ClickType.FLAG);
    }

    @Test
    void isGameWon() {
    }

    @Test
    void gameOver() {
    }

    @Test
    void giveUp() {
    }

    @Test
    void getFlaggedMinesCount() {
    }

    @Test
    void getTotalMineCount() {
    }

    @Test
    void getBoard() {
    }
}