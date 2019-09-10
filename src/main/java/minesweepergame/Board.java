package minesweepergame;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.*;
import java.util.function.Consumer;

import static minesweepergame.MinesweeperGame.ClickType;

//Possible values:


public class Board {
    private CellType[][] board;
    private Set<Point> mineLocations = new HashSet<>();

    Set<Point> getMineLocations() {
        return mineLocations;
    }

    private Set<Point> flagLocations = new HashSet<>();

    public Set<Point> getFlagLocations(){
        return flagLocations;
    }

    public boolean addFlagLocation(Point location){
        return flagLocations.add(location);
    }

    Board(int width, int height)  throws Exception  {
        this(width, height, 0, -1);
    }

    Board(int width, int height, int numberOfmines)  throws Exception  {
        this(width, height, numberOfmines, -1);
    }

    Board(int width, int height, int numberOfmines, int seed) throws Exception {
        this(width, height, generateMineSet(width, height,numberOfmines, seed));
    }

    Board(int width, int height, Set<Point> locations) throws Exception {

        if (width <= 0 || height <= 0){
            //check for invalid sizes in tests
            throw new IllegalArgumentException("Height and Width must be at least 1");
        }

        CellType[][] boardStart = new CellType[height][width];
        for (int i = 0; i < height; i++){
            Arrays.fill(boardStart[i], CellType.BLANK);
        }

        board = boardStart;
        //Make sure to check if locations are off the board
        //if(Collections.locations)
        mineLocations = new HashSet<Point>(locations);
    }

    private static Set<Point> generateMineSet(int width, int height, int numberOfmines, int seed) throws Exception {
        if (numberOfmines > width * height){
            throw new Exception("You have tried to create a board with too mines");
        }
        Set<Point> locations = new HashSet<>();
        Random rand;
        rand = (seed==-1) ? new Random(): new Random(seed);
        if (numberOfmines < 1){
            numberOfmines = defaultmineCount(width, height);
        }

        for (int i = 0; i < numberOfmines; i++){
            addNewmine(width, height, locations, rand);
        }
        return locations;
    }

    private static void addNewmine(int width, int height, Set<Point> locationSet, Random rand) {
        while(!locationSet.add(randomPoint(width, height, rand))){}
    }

    int getWidth(){
        return board[0].length;
    }
    int getHeight(){
        return board.length;
    }

    CellType[][] getBoard(){
        CellType[][] boardValue = new CellType[getHeight()][getWidth()];
        for(int j = 0 ; j < getHeight(); j++){
            boardValue[j] = Arrays.copyOf(board[j], getWidth());
        }
        return boardValue;
    }

    //replace mine for mines
    boolean checkFormine(Point location){
        for(Point mineCheck: getMineLocations()){
            if (location.equals(mineCheck)) {
                return true;
            }
        }
        return false;
    }

    boolean click(Point location, ClickType clickType) throws Exception {

        if(getFlagLocations().contains(location)){
            getFlagLocations().remove(location);
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
        return getMineLocations().size();
    }
    public int getFlaggedCount(){
        return getFlagLocations().size();
    }

    public void revealBoard(){
        getMineLocations().forEach(point ->
        {
            if (board[point.y][point.x] == CellType.FLAG){
                //do flags and then reveal board to show `Found` mines
                board[point.y][point.x] = CellType.FOUND;
            }
            else {
                board[point.y][point.x] = CellType.MINE;
            }

        });
    }
    private static Point randomPoint(int width, int height, Random rand){
        Point location = new Point();
        //Create the mine locations
        location.x = rand.nextInt(width);
        location.y = rand.nextInt(height);
        return location;
    }
    private static int defaultmineCount(int width, int height){
        int tenPercent = (width * height)/10;
        return (tenPercent > 1) ? tenPercent : 1;
    }

    private int findSurroundingMines(Point location){
        //take in location
        //find all surrounding locations
        //Hacky?
        final Integer[] count = {0};
        forEachSurrounding(location, testPoint -> {
            if (getMineLocations().contains(testPoint)) {
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
                if (!checkFormine(location)) {
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
                if (getFlagLocations().size() >= getMineLocations().size()) {
                    throw new Exception("Not allowed to have more flags than mines");
                }
                addFlagLocation(location);
                board[location.y][location.x] = CellType.FLAG;
                return true;
            }
            if (clickType == ClickType.BLANK) {
                board[location.y][location.x] = CellType.BLANK;
                return true;
            }
        }//Throw exception
        return true;
    }

    //Check if mines and flags are in the same locations
    public boolean flaggedAllMines(){
        return getFlagLocations().containsAll(getMineLocations());
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

        @Override
        public String toString(){
            return Character.toString(getAction());
        }

    }
}
