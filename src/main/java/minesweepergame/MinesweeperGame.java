package minesweepergame;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.Set;


public class MinesweeperGame {

    private Board board;
    GameState currentState = GameState.READY;

    public static char[][] updateBoard(char[][] board, int[] click) {
        //convert char board to Cell Type board
        //Click on CellType board
        //Convert back to Char board
        return null;
    }

    public MinesweeperGame() {
        //needs a board

    }

    public void click(Point location, ClickType clickType) throws Exception{
        if(currentState == GameState.ACTIVE){
            if(location.x > 0 || location.x < board.getWidth() || location.y > 0 || location.y < board.getHeight()) {
                if (!board.click(location, clickType)) {
                    endGame();
                }
            }
            else{
                throw new Exception("Did not click on the Board X: " + location.x + " Y: " + location.y+ " Actual Board is: " + board.getHeight() + " by " +board.getWidth());
            }
        }
        else{
            throw new Exception("Not ready to play because game state is: " + currentState.toString());
        }
    }

    //Game check to see if you are done
    public void endGame() throws Exception {
        if(currentState != GameState.ACTIVE){
            throw new Exception("Not ready to play because game state is: " + currentState.toString());
        }
        if(board.flaggedAllMines()){
            currentState = GameState.WON;
        }
        else{
            currentState = GameState.LOST;
        }
        board.revealBoard();
    }

    public GameState getGameState(){
        return currentState;
    }

    //Need to determine if mine was found and end the game
    //
    //Need to quit game if desired
    public void startNewGame( int width, int height) throws Exception {
        //initialize board with all E characters
        startNewGame(new Board(width, height));
    }

    //maybe not public
    void startNewGame( int width, int height, int numberOfmines) throws Exception {
        //initialize board with all E characters
        startNewGame(new Board(width, height, numberOfmines));
    }

    void startNewGame( int width, int height, int numberOfmines, int seed) throws Exception {
        //initialize board with all E characters
        startNewGame(new Board(width, height, numberOfmines, seed));
    }
    void startNewGame( int width, int height, Set<Point> locations) throws Exception {
        //initialize board with all E characters
        startNewGame(new Board(width, height, locations));
    }
    void startNewGame(Board board) throws Exception {
        //initialize board with all E characters
        this.board = board;
        currentState = GameState.ACTIVE;
    }
    /*void startNewGameForTests( int width, int height, Point minePlacement) throws Exception {
        //initialize board with all E characters
        board = new Board(width, height, numberOfmines, seed);
        currentState = GameState.ACTIVE;
    }*/

    public int getFlaggedMinesCount(){
        return board.getFlaggedCount();
    }

    public int getTotalMineCount(){
        return board.getTotalMineCount();
    }

    public void displayBoard(PrintStream stream){
        Board.CellType[][] currentBoard = board.getBoard();
        for(int j = 0 ; j < board.getWidth(); j++){
            stream.println(StringUtils.join(currentBoard[j], " "));
        }
    }

    Board.CellType[][] getBoard(){
       return board.getBoard();
    }

    enum ClickType{
        BLANK,
        FLAG,
        QUESTION,
        TEST;
    }

    enum GameState{
        READY,
        ACTIVE,
        WON,
        LOST;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        MinesweeperGame game = new MinesweeperGame();
        game.startNewGame(10, 10);

        //need user to check if the game is won
        while(game.getGameState() == GameState.ACTIVE){
            game.displayBoard(System.out);
            String input = obj.readLine();
            //parse input string: X Y TYPE
            String[] inputArray = input.split(" ");
            game.click(new Point(Integer.valueOf(inputArray[0]), Integer.valueOf(inputArray[1])),
                    ClickType.valueOf(inputArray[2].toUpperCase()));
        }
        if(game.getGameState() == GameState.LOST){
            System.out.println("Hit a mine");
        }
        else if (game.getGameState() == GameState.WON){
            System.out.println("Congrats you win!");
        }
    }
}


/*
User paths:

 */