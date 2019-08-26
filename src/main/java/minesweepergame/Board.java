package minesweepergame;

import java.awt.*;
import java.util.*;
import java.util.function.Consumer;

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
            //check for invalid sizes in tests
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
        //Make sure first click is OK
        if (CellType.ClickableType(board[location.y][location.x])){
            return tryClick(location, clickType);
        }
        else{
            throw new Exception("Not an allowed click");
        }
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
                //do flags and then reveal board to show `Found` bombs
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
        //Hacky?
        final Integer[] count = {0};
        forEachSurrounding(location, testPoint -> {
            if (bombLocations.contains(testPoint)) {
                count[0]++;
            }
        });
        //return the count of mines near those locations
        return count[0];
    }

    //find each surrounding things
    private void forEachSurrounding(Point location, Consumer<Point> action) {
        Objects.requireNonNull(action);
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                Point testPoint = new Point((location.x + i), (location.y + j));
                if(testPoint.x >= 0 && testPoint.x < getWidth() && testPoint.y >=0 && testPoint.y < getHeight()) {
                    action.accept(testPoint);
                }
            }
        }
    }
    //return the cell type instead
    private boolean tryClick(Point location, ClickType clickType) throws Exception{
        if (CellType.ClickableType(board[location.y][location.x])) {
            if (clickType == ClickType.TEST) {
                if (!checkForBomb(location)) {
                    int cellTypeNumber = findSurroundingMines(location);
                    board[location.y][location.x] = CellType.fromInterger(cellTypeNumber);
                    //maybe move this to the Minesweeper game
                    if (cellTypeNumber == 0) {
                        forEachSurrounding(location, testPoint -> {
                            try {
                                tryClick(testPoint, ClickType.TEST);
                            } catch (Exception e) {
                                throw new RuntimeException("This should not happen", e);
                            }
                        });
                    }
                    return true;
                } else {
                    board[location.y][location.x] = CellType.MINE;
                    return false;
                }
            }
            if (clickType == ClickType.QUESTION) {
                board[location.y][location.x] = CellType.QUESTION;
                return true;
            }
            if (clickType == ClickType.FLAG) {
                if (flagLocations.size() >= bombLocations.size()) {
                    throw new Exception("Not allowed to have more flags than mines");
                }
                flagLocations.add(location);
                board[location.y][location.x] = CellType.FLAG;
                return true;
            }
            if (clickType == ClickType.BLANK) {
                board[location.y][location.x] = CellType.BLANK;
                return true;
            }
        }
        return true;
    }

    //Check if mines and flags are in the same locations
    public boolean flaggedAllMines(){
        return flagLocations.containsAll(bombLocations);
    }


    interface CellTypeBase {

        public char getValue();
    }

    static class CharCell implements CellTypeBase {
        private char value;

        protected CharCell(){
            this(' ');
        }
        protected CharCell(char value) {
            this.value = value;
        }

        public char getValue() {
            return  value;
        }


    }

    static class CellTypeContiainer extends CharCell {
        static CellTypeBase BLANK = new CharCell('B');
        /*public CellTypeContiainer(){
            base(' ');
        };*/
    }

    enum CellType{

        //can I use an index here instead?
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

        public static boolean ClickableType(CellType currentValue){
            if (currentValue == BLANK || currentValue == FLAG || currentValue == QUESTION){
                return true;
            }
            else {
                return false;
            }
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
