package minesweepergame;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;



public class MinesweeperGame {

    private Board board;

    public static char[][] updateBoard(char[][] board, int[] click) {
        return null;
    }

    public MinesweeperGame() {
        //needs a board

        //needs to hide mines
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

    public void click(Point location, ClickType clickType){

    }

    //Need to determine if the user won
    //  All blank spaces have been clicked
    //  All mines have been covered with flags
    //  checked after every click
    //  check after test option
    public boolean isGameWon(){
        return false;
    }

    public void gameOver(){

    }

    //Need to determine if mine was found and end the game
    //
    //Need to quit game if desired
    public void startNewGame( int width, int height) throws Exception {
        //initialize board with all E characters
        board = new Board(width, height);

        //Create the mine locations


    }
    //Give up and show me board
    public void giveUp(){

    }
    //Test if I won option
    public int getFlaggedMinesCount(){
        return 1;
    }

    public int getTotalMineCount(){
        return 1;

    }
    public Board.CellType[][] getBoard(){
        return board.getBoard();
    }


    enum ClickType{
        FLAG, BLANK, QUESTION, TEST;
    }
}
