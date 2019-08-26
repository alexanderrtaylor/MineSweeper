package minesweepergame;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;



public class MinesweeperGame {

    private Board board;
    private boolean gameOver = false;

    public static char[][] updateBoard(char[][] board, int[] click) {
        return null;
    }

    public MinesweeperGame() {
        //needs a board

    }

    //need to click somewhere
    //  empty space and spaces nearby are marked based on surrounding mines
    //  mine is found-> lose game
    //Need to mark potential mines
    //  only works on an unclicked space/blank space
    //  three way toggle
    //  flag
    //  ?
    //  blank

    public boolean click(Point location, ClickType clickType) throws Exception{
        return board.click(location, clickType);
    }

    //Need to determine if the user won
    //  All blank spaces have been clicked
    //  All mines have been covered with flags
    //  checked after every click
    //  check after test option
    public boolean isGameWon()
    {
        return board.flaggedAllMines();
    }

    public void endGame(){
        board.revealBoard();
        gameOver = true;

    }

    public boolean isGameOver(){
        if(isGameWon()){
            return true;
        }
        return gameOver;
    }

    //Need to determine if mine was found and end the game
    //
    //Need to quit game if desired
    public void startNewGame( int width, int height) throws Exception {
        //initialize board with all E characters
        board = new Board(width, height);
    }

    //maybe not public
    public void startNewGame( int width, int height, int numberOfBombs) throws Exception {
        //initialize board with all E characters
        board = new Board(width, height, numberOfBombs);
    }

    void startNewGame( int width, int height, int numberOfBombs, int seed) throws Exception {
        //initialize board with all E characters
        board = new Board(width, height, numberOfBombs, seed);
    }
    //Give up and show me board
    public void giveUp(){
        endGame();
    }

    public int getFlaggedMinesCount(){
        return board.getFlaggedCount();
    }

    public int getTotalMineCount(){
        return board.getTotalMineCount();
    }
    public void displayBoard(PrintStream stream){
        Board.CellType[][] currentBoard = board.getBoard();
        for (int i = 0; i < board.getHeight(); i++){
            for(int j = 0 ; j < board.getWidth(); j++){
                stream.print(currentBoard[j][i].getAction()  + " ");
            }
            stream.println();
        }
    }

    enum ClickType{
        BLANK,
        FLAG,
        QUESTION,
        TEST;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        MinesweeperGame game = new MinesweeperGame();
        game.startNewGame(10, 10);

        //need user to check if the game is won
        while(!game.isGameOver()){
            game.displayBoard(System.out);
            String input = obj.readLine();
            //parse input string: X Y TYPE
            String[] inputArray = input.split(" ");
            if(!game.click(new Point(Integer.valueOf(inputArray[0]), Integer.valueOf(inputArray[1])),
                    ClickType.valueOf(inputArray[2].toUpperCase()))){
                System.out.println("Hit a mine");
                game.endGame();
            }


        }
    }
}
