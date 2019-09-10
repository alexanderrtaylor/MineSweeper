package minesweepergame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest{
    
    Point mineLocationPoint;
    Point notmineLocationPoint1;
    Point notmineLocationPoint2;
    Board newBoard;
    Board newBoard2;
    Board.CellType[][] expectedBoard;
    
    @BeforeEach
    void initialize(){
        mineLocationPoint = new Point();
        notmineLocationPoint1 = new Point(0, 0);
        notmineLocationPoint2 = new Point(1, 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 30})
    void getWidthVaryingSizes(int number) throws Exception {
        newBoard = new Board(number, 11);
        assertEquals(number, newBoard.getWidth());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 30})
    void getHeightVaryingSizes(int number) throws Exception {
        newBoard = new Board(11, number);
        assertEquals(number, newBoard.getHeight());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 20, 30})
    void getmineVaryingCounts(int number) throws Exception {
        newBoard = new Board(number + 1, number + 2, number);
        assertEquals(number, newBoard.getTotalMineCount());
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "5:10", "10:20", "25:10", "10:7"}, delimiter = ':')
    void getBoardVaryingSizes(int x, int y) throws Exception {
        newBoard = new Board(x, y);
        expectedBoard = new Board.CellType[y][x];
        for (int i = 0; i < y; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        assertArrayEquals(expectedBoard, newBoard.getBoard());
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "5:10", "10:20", "25:10", "10:7"}, delimiter = ':')
    void checkFormineTest(int x, int y) throws Exception {
        newBoard = new Board(x, y, 1, 0);
        expectedBoard = new Board.CellType[y][x];
        for (int i = 0; i < y; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        assertArrayEquals(expectedBoard, newBoard.getBoard());
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2:1:0:1", "5:10:0:8:0", "10:20:5:8:0", "25:10:10:8:0", "10:7:5:4:0"}, delimiter = ':')
    void clickTest(int x, int y, int mine_x,  int mine_y, int nearbyMines) throws Exception {
        //TODO: Check for numbers being modified as flags and question marks
        newBoard = new Board(x, y, 1, 1);
        mineLocationPoint.x = mine_x;
        mineLocationPoint.y = mine_y;

        Board.CellType[][] expectedBoard = new Board.CellType[y][x];
        for (int i = 0; i < y; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        assertArrayEquals(expectedBoard, newBoard.getBoard());

        assertFalse(newBoard.click(mineLocationPoint, MinesweeperGame.ClickType.TEST));
        assertTrue(newBoard.click(notmineLocationPoint1, MinesweeperGame.ClickType.TEST));

        expectedBoard[0][0] = Board.CellType.fromInterger(nearbyMines);
        expectedBoard[mineLocationPoint.y][mineLocationPoint.x] = Board.CellType.MINE;
        assertArrayEquals(expectedBoard, newBoard.getBoard());
        assertTrue(newBoard.click(mineLocationPoint, MinesweeperGame.ClickType.FLAG));
        assertThrows(Exception.class, () -> newBoard.click(notmineLocationPoint1, MinesweeperGame.ClickType.FLAG));

        newBoard2 = new Board(x, y, 1, 1);
        expectedBoard[0][0] = Board.CellType.BLANK;


        assertTrue(newBoard2.click(mineLocationPoint, MinesweeperGame.ClickType.FLAG));
        assertTrue(newBoard2.click(mineLocationPoint, MinesweeperGame.ClickType.FLAG));
        assertThrows(Exception.class, () -> newBoard2.click(notmineLocationPoint1, MinesweeperGame.ClickType.FLAG));
        expectedBoard[mine_y][mine_x] = Board.CellType.FLAG;
        assertArrayEquals(expectedBoard, newBoard2.getBoard());
        assertTrue(newBoard2.click(mineLocationPoint, MinesweeperGame.ClickType.BLANK));
        assertTrue(newBoard2.click(notmineLocationPoint2, MinesweeperGame.ClickType.FLAG));


        //unset the flag and ensure I am allowed to add new ones
        assertTrue(newBoard2.click(mineLocationPoint, MinesweeperGame.ClickType.BLANK));
        assertTrue(newBoard2.click(notmineLocationPoint1, MinesweeperGame.ClickType.BLANK));
        assertTrue(newBoard2.click(notmineLocationPoint2, MinesweeperGame.ClickType.BLANK));
        assertTrue(newBoard2.click(notmineLocationPoint1, MinesweeperGame.ClickType.FLAG));
        expectedBoard[mine_y][mine_x] = Board.CellType.BLANK;
        expectedBoard[0][0] = Board.CellType.FLAG;
        assertArrayEquals(expectedBoard, newBoard2.getBoard());

        assertTrue(newBoard2.click(notmineLocationPoint1, MinesweeperGame.ClickType.QUESTION));
        assertTrue(newBoard2.click(mineLocationPoint, MinesweeperGame.ClickType.FLAG));
        expectedBoard[mine_y][mine_x] = Board.CellType.FLAG;
        expectedBoard[0][0] = Board.CellType.QUESTION;
        assertArrayEquals(expectedBoard, newBoard2.getBoard());

    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "5:10", "10:20", "25:10", "10:7"}, delimiter = ':')
    void getFlaggedCountTest(int x, int y) throws Exception{

        newBoard = new Board(x, y, 1, 1);;

        assertEquals(0, newBoard.getFlaggedCount());
        assertTrue(newBoard.click(notmineLocationPoint1, MinesweeperGame.ClickType.FLAG));
        assertEquals(1, newBoard.getFlaggedCount());

        assertThrows(Exception.class, () -> newBoard.click(notmineLocationPoint2, MinesweeperGame.ClickType.FLAG));
        assertEquals(1, newBoard.getFlaggedCount());

        assertTrue(newBoard.click(notmineLocationPoint1, MinesweeperGame.ClickType.BLANK));
        assertEquals(0, newBoard.getFlaggedCount());
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2:1:0", "5:10:0:8", "10:20:5:8", "25:10:10:8", "10:7:5:4"}, delimiter = ':')
    void revealBoardTest(int x, int y, int mine_x,  int mine_y) throws Exception {
        newBoard = new Board(x, y, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[y][x];
        for (int i = 0; i < y; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        assertArrayEquals(expectedBoard, newBoard.getBoard());
        newBoard.revealBoard();
        expectedBoard[mine_y][mine_x] = Board.CellType.MINE;
        assertArrayEquals(expectedBoard, newBoard.getBoard());
    }

    //initilizing board without a given mine count
    //initializing board with a given mine count
    //initializing board with a bad mine count

    //Sizes of boards:
        /*
            Even boards(square)
            Rectangular boards
            Really Tall boards
            Really wide boards
            Boards that should not be allowed(null/negative numbers)
            maximum size of the board
            boards that are smaller than 10
            size 1 board(is it legal?)
            board whose mines number is size
            board whose mines number is size-1
            boards that are smaller than the number of mines
         */

    @ParameterizedTest
    @CsvSource(value = {"2:2:1", "5:10:5", "10:20:10", "25:10:20", "10:7:0"}, delimiter = ':')
    void getTotalMineCountTest(int x, int y, int mine_count) throws Exception {


        if (mine_count == 0){
            newBoard = new Board(x, y);
            assertEquals(7, newBoard.getTotalMineCount());
        }
        else {
            newBoard = new Board(x, y, mine_count, 1);
            assertEquals(mine_count, newBoard.getTotalMineCount());
        }

    }
}