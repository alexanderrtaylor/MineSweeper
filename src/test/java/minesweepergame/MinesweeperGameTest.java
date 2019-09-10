package minesweepergame;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


import static org.junit.jupiter.api.Assertions.*;

class MinesweeperGameTest {
    String fiveByFiveBoardInit =
            "B B B B B\n" +
            "B B B B B\n" +
            "B B B B B\n" +
            "B B B B B\n" +
            "B B B B B\n";

    String fiveByFiveBoardOneTest =
            "0 0 0 0 0\n" +
            "0 1 1 1 0\n" +
            "0 1 B 1 0\n" +
            "0 1 1 1 0\n" +
            "0 0 0 0 0\n";
    String fiveByFiveBoardOneTestOneFlag = StringUtils.join(new String[] {
            "0 0 0 0 0\n" ,
            "0 1 1 1 0\n" ,
            "0 1 F 1 0\n" ,
            "0 1 1 1 0\n" ,
            "0 0 0 0 0\n"});

    Board.CellType[][] fiveByFiveBoard = new Board.CellType[5][5];
    MinesweeperGame testMinesweeper;

    @BeforeEach
    void runSetup() throws Exception{
        //MockitoAnnotations.initMocks(this);
        fiveByFiveBoard = new Board(5, 5).getBoard();
        testMinesweeper = new MinesweeperGame();
    }

    @Test
    void startNewGameTest() throws Exception {
        //begin a new board
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
    void startNewGameTestOneTest() throws Exception {
        //begin a new board
        testMinesweeper.startNewGame(5 ,5, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[10][10];
        for (int i = 0; i < 10; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        String boardContent = fiveByFiveBoardInit;
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(1, 1), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        boardContent = boardContent + fiveByFiveBoardOneTest;
        assertEquals(boardContent, outContent.toString());
    }

    @Test
    void startNewGameTestTwoTest() throws Exception {
        //begin a new board
        testMinesweeper.startNewGame(5 ,5, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[10][10];
        for (int i = 0; i < 10; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        String boardContent = fiveByFiveBoardInit;
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(1, 1), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        boardContent = boardContent + fiveByFiveBoardOneTest;
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(0, 4), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertEquals(boardContent, outContent.toString());

    }
    @Test
    void startNewGameTestTwoTestOneFlag() throws Exception {
        //begin a new board
        testMinesweeper.startNewGame(5 ,5, 1, 1);
        Board.CellType[][] expectedBoard = new Board.CellType[10][10];
        for (int i = 0; i < 10; i++){
            Arrays.fill(expectedBoard[i], Board.CellType.BLANK);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        String boardContent = fiveByFiveBoardInit;
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(1, 1), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        boardContent = boardContent + fiveByFiveBoardOneTest;
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(0, 4), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertEquals(boardContent, outContent.toString());
        testMinesweeper.click(new Point(0, 3), MinesweeperGame.ClickType.FLAG);
        testMinesweeper.displayBoard(os);
        assertEquals(boardContent, outContent.toString());
        assertEquals(MinesweeperGame.GameState.ACTIVE, testMinesweeper.getGameState());
        testMinesweeper.endGame();
        assertEquals(MinesweeperGame.GameState.WON, testMinesweeper.getGameState());
    }


    @Test
    void updateBoard() {
    }

    @Test
    void clickTest() throws Exception {
        testMinesweeper.startNewGame(10 ,10);
        Point testPoint = new Point(1, 1);
        testMinesweeper.click(testPoint, MinesweeperGame.ClickType.FLAG);
    }

    //All the following with a simple 5x5 board size
    @Test
    void happyPathtest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTest));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //flagging the mine
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTestOneFlag));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //checking if I won
        testMinesweeper.endGame();
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.WON));
    }
    @Test
    void expansionTestSetSideBombSideClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(0, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.BLANK, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void expansionTestSetSideBombCornerClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(0, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.BLANK, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(4, 4), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void expansionTestSetSideBombMiddleClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(0, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.BLANK, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.ONE, Board.CellType.ONE, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void expansionTestSetCornerBombSideClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(4, 4)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.ONE},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.BLANK}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 2), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void expansionTestSetCornerBombCornerClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(4, 4)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.ONE},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.BLANK}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void expansionTestSetCornerBombMiddleClick() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(4, 4)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard = new Board.CellType[][]{
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.ONE},
                {Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.EMPTY, Board.CellType.ONE, Board.CellType.BLANK}};
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }
    @Test
    void hitMineTest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        fiveByFiveBoard[2][2] = Board.CellType.MINE;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.LOST));
    }
    @Test
    void difficultWin() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        fiveByFiveBoard[1][1] = Board.CellType.ONE;
        testMinesweeper.click(new Point(1, 1), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[1][2] = Board.CellType.ONE;
        testMinesweeper.click(new Point(2, 1), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[1][3] = Board.CellType.ONE;
        testMinesweeper.click(new Point(3, 1), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[2][1] = Board.CellType.ONE;
        testMinesweeper.click(new Point(1, 2), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[2][3] = Board.CellType.ONE;
        testMinesweeper.click(new Point(3, 2), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[3][1] = Board.CellType.ONE;
        testMinesweeper.click(new Point(1, 3), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[3][2] = Board.CellType.ONE;
        testMinesweeper.click(new Point(2, 3), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        fiveByFiveBoard[3][3] = Board.CellType.ONE;
        testMinesweeper.click(new Point(3, 3), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));

        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTest));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //flagging the mine
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTestOneFlag));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //checking if I won
        testMinesweeper.endGame();
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.WON));
    }
    @Test
    void offBoardClickTest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        assertThrows(Exception.class, () -> testMinesweeper.click(new Point(5, 0), MinesweeperGame.ClickType.TEST));
    }
    @Test
    void giveUpTest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.endGame();
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.LOST));
    }
    @Test
    void changeNonMineSquareTest() throws Exception {
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.FLAG);
        fiveByFiveBoard[0][0] = Board.CellType.FLAG;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.QUESTION);
        fiveByFiveBoard[0][0] = Board.CellType.QUESTION;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.BLANK);
        fiveByFiveBoard[0][0] = Board.CellType.BLANK;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTest));
    }
    @Test
    void changeSquareTest() throws Exception {
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        fiveByFiveBoard[0][0] = Board.CellType.FLAG;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.QUESTION);
        fiveByFiveBoard[0][0] = Board.CellType.QUESTION;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.BLANK);
        fiveByFiveBoard[0][0] = Board.CellType.BLANK;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
    }
    @Test
    void tooManyFlagsTest() throws Exception {
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.FLAG);
        assertThrows(Exception.class, () -> testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG));
    }
    @Test
    void tooManyFlagsFixTest() throws Exception {
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.FLAG);
        assertThrows(Exception.class, () -> testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.QUESTION);
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.BLANK);
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
    }


    @Test
    void flagAndCheckTest() throws Exception {
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        testMinesweeper.endGame();
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.WON));
    }
    @Test
    void badMineNumberTest() throws Exception {
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        assertThrows(Exception.class, () -> testMinesweeper.startNewGame(5, 5, 1000, 1));
    }
    @Test
    void badGameSizeTest() throws Exception {
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        assertThrows(Exception.class, () -> testMinesweeper.startNewGame(0, 0, 0, 1));
    }
    @Test
    void clickBeforeGameTest() throws Exception {
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        assertThrows(Exception.class, () -> testMinesweeper.startNewGame(0, 0, 0, 1));
    }
    @Test
    void revealBeforeGameTest() throws Exception {
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        assertThrows(Exception.class, () -> testMinesweeper.endGame());
    }
    @Test
    void playPastHappyPathTest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTest));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //flagging the mine
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.FLAG);
        testMinesweeper.displayBoard(os);
        assertThat(outContent.toString(), endsWith(fiveByFiveBoardOneTestOneFlag));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        //checking if I won
        testMinesweeper.endGame();
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.WON));
        assertThrows(Exception.class, () -> testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST));
    }
    @Test
    void playPastHitMineTest() throws Exception {
        //begin a new board with specific mine placement
        Set<Point> locations = new HashSet<>(Arrays.asList(new Point(2, 2)));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.READY));
        testMinesweeper.startNewGame(5, 5, locations);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream os = new PrintStream(outContent);
        testMinesweeper.displayBoard(os);
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        //clicking a single location
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.ACTIVE));
        testMinesweeper.click(new Point(2, 2), MinesweeperGame.ClickType.TEST);
        testMinesweeper.displayBoard(os);
        fiveByFiveBoard[2][2] = Board.CellType.MINE;
        assertThat(testMinesweeper.getBoard(), arrayContaining(fiveByFiveBoard));
        assertThat(testMinesweeper.getGameState(), equalTo(MinesweeperGame.GameState.LOST));
        assertThrows(Exception.class, () -> testMinesweeper.click(new Point(0, 0), MinesweeperGame.ClickType.TEST));
    }
}