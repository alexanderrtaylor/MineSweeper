package minesweepergame;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperGameTest {

    /* Array Possibility 10/10
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 1 1 1
        0 0 0 0 0 0 0 1 B B
        0 0 0 0 0 0 0 1 1 1
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0 0
     */

    @Test
    void startNewGameTest() throws Exception {
        //begin a new board
        MinesweeperGame testMinesweeper = new MinesweeperGame();
        testMinesweeper.startNewGame(10 ,10, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[10][10];
        for (int i = 0; i < 10; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);

        String boardContent = "";
        for (int i = 0; i < 10; i++){
            for(int j = 0 ; j < 10; j++){
                boardContent += (expectedBoard[j][i].getAction()  + " ");
            }
            boardContent += "\n";
        }
        assertEquals(boardContent, outContent.toString());
    }

    @Test
    void startNewGameTestClick() throws Exception {
        //begin a new board
        MinesweeperGame testMinesweeper = new MinesweeperGame();
        testMinesweeper.startNewGame(10 ,10, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[10][10];
        for (int i = 0; i < 10; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);

        String boardContent = "";
        for (int i = 0; i < 10; i++){
            for(int j = 0 ; j < 10; j++){
                boardContent += (expectedBoard[j][i].getAction()  + " ");
            }
            boardContent += "\n";
        }
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(1, 1), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        boardContent = boardContent + "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 1 1 1 \n" +
                "0 0 0 0 0 0 0 1 B B \n" +
                "0 0 0 0 0 0 0 1 1 1 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n" +
                "0 0 0 0 0 0 0 0 0 0 \n";
        assertEquals(boardContent, outContent.toString());
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