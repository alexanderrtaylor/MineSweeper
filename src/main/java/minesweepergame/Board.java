package minesweepergame;

import javafx.scene.control.Cell;

import java.awt.*;
import java.util.*;

import static minesweepergame.MinesweeperGame.ClickType;

//Possible values:


public class Board {
    private CellType[][] board;
    private Set<Point> bombLocations = new HashSet<>();
    Random rand;
    private Set<Point> flagLocations = new HashSet<>();

    Board(int width, int height)  throws Exception  {
        this(width, height, 0, -1);
    }

    Board(int width, int height, int numberOfBombs)  throws Exception  {
        this(width, height, numberOfBombs, -1);
    }

    Board(int width, int height, int numberOfBombs, int seed) throws Exception {

        if (width <= 0 || height <= 0){
            throw new IllegalArgumentException("Height and Width must be at least 1");
        }

        CellType[][] boardStart = new CellType[height][width];
        for (int i = 0; i < height; i++){
            Arrays.fill(boardStart[i], CellType.BLANK);
        }

        rand = (seed==-1) ? new Random(): new Random(seed);
        board = boardStart;

        if (numberOfBombs < 1){
            numberOfBombs = defaultBombCount();
        }

        for (int i = 0; i < numberOfBombs; i++){
            addNewBomb();
        }
    }

    private void addNewBomb() {
        int width = getWidth();
        int height = getHeight();
        while(!bombLocations.add(randomPoint(width, height))){}
    }

    int getWidth(){
        return board[0].length;
    }
    int getHeight(){
        return board.length;
    }

    CellType[][] getBoard(){
        return board;
    }

    boolean checkForBomb(Point location){
        for(Point bombCheck: bombLocations){
            if (location.equals(bombCheck)) {
                return true;
            }
        }
        return false;
    }

    boolean click(Point location, ClickType clickType) throws Exception {

        if(flagLocations.contains(location)){
            flagLocations.remove(location);
        }
        if(board[location.y][location.x] == CellType.EMPTY){
            throw new Exception("Not allowed to flag an Empty Cell");
        }

        if(clickType == ClickType.TEST) {
            if (!checkForBomb(location)) {
                int cellTypeNumber = findSurroundingMines(location);
                board[location.y][location.x] = CellType.fromInterger(cellTypeNumber);
                /*for(int i = -1; i <= 1; i++){
                    for(int j = -1; j <= 1; j++){
                        if(i == 0 && j == 0){
                            continue;
                        }
                        Point testPoint = new Point((location.x + i), (location.y + j));
                        if(!checkForBomb(location)) {
                            findSurroundingMines(testPoint);
                        }
                    }
                }*/
                return true;
            } else {
                board[location.y][location.x] = CellType.MINE;
                return false;
            }
        }
        if(clickType == ClickType.QUESTION) {
            board[location.y][location.x] = CellType.QUESTION;
            return true;
        }
        if(clickType == ClickType.FLAG) {
            if(flagLocations.size() >= bombLocations.size()){
                throw new Exception("Not allowed to have more flags than mines");
            }
            flagLocations.add(location);
            board[location.y][location.x] = CellType.FLAG;
            return true;
        }
        if(clickType == ClickType.BLANK){
            board[location.y][location.x] = CellType.BLANK;
            return true;
        }
        return false;
    }
    public int getTotalMineCount(){
        return bombLocations.size();
    }
    public int getFlaggedCount(){
        return flagLocations.size();
    }

    public void revealBoard(){
        bombLocations.forEach(point ->
        {
            if (board[point.y][point.x] == CellType.FLAG){
                board[point.y][point.x] = CellType.FOUND;
            }
            else {
                board[point.y][point.x] = CellType.MINE;
            }

        });
    }
    private Point randomPoint(int width, int height){
        Point location = new Point();
        //Create the mine locations
        location.x = rand.nextInt(width);
        location.y = rand.nextInt(height);
        return location;
    }
    private int defaultBombCount(){
        int tenPercent = (getWidth() * getHeight())/10;
        return (tenPercent > 1) ? tenPercent : 1;
    }

    private int findSurroundingMines(Point location){
        //take in location
        //find all surrounding locations
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                Point testPoint = new Point((location.x + i), (location.y + j));
                if(bombLocations.contains(testPoint)){
                    count++;
                }
            }
        }
        //return the count of mines near those locations
        return count;
    }

    enum CellType{

        BLANK('B'),
        FLAG('F'),
        QUESTION('?'),
        MINE('M'),
        FOUND('X'),
        EMPTY('0'),
        ONE('1'),
        TWO('2'),
        THREE('3'),
        FOUR('4'),
        FIVE('5'),
        SIX('6'),
        SEVEN('7'),
        EIGHT('8');

        // declaring private variable for getting values
        private char action;

        // getter method
        public char getAction()
        {
            return this.action;
        }

        // enum constructor - cannot be public or protected
        private CellType(char action)
        {
            this.action = action;
        }

        private static final Map<Character, CellType> byId = new HashMap<>();

        static {
            for (CellType e : CellType.values()) {
                if (byId.put(e.getAction(), e) != null) {
                    throw new IllegalArgumentException("duplicate id: " + e.getAction());
                }
            }
        }

        public static final CellType fromInterger(int value){
            return CellType.byId.get(Integer.toString(value).charAt(0));
        }

    }
}
