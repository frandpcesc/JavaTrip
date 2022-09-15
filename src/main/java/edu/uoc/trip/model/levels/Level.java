package edu.uoc.trip.model.levels;

import edu.uoc.trip.model.cells.*;
import edu.uoc.trip.model.utils.Coordinate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class that represents each level of the game.
 *
 * @author David García-Solórzano
 * @author Francesc Dentí
 * @version 1.0
 */
public class Level {

    /**
     * Size of the board, i.e. size x size.
     */
    private int size;

    /**
     * Difficulty of the level
     */
    private LevelDifficulty difficulty;

    /**
     * Representation of the board.
     */
    private Cell[][] board;

    /**
     * Number of moves that the player has made so far.
     */
    private int numMoves = 0;

    /**
     * Minimum value that must be assigned to the attribute "size".
     */
    private static final int MINIMUM_BOARD_SIZE = 3;

    /**
     * Constructor
     *
     * @param fileName Name of the file that contains level's data.
     * @throws LevelException When there is any error while parsing the file.
     */
    public Level(String fileName) throws LevelException {
        setNumMoves(0);
        parse(fileName);
    }

    /**
     * Parses/Reads level's data from the given file.<br/>
     * It also checks which the board's requirements are met.
     *
     * @param fileName Name of the file that contains level's data.
     * @throws LevelException When there is any error while parsing the file
     * or some board's requirement is not satisfied.
     */
    private void parse(String fileName) throws LevelException{
        boolean isStarting = false;
        boolean isFinish = false;
        String line;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader.getResourceAsStream(fileName));

        try(InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader)){

            line = getFirstNonEmptyLine(reader);

            if (line  != null) {
                setSize(Integer.parseInt(line));
            }

            line = getFirstNonEmptyLine(reader);

            if (line != null) {
                setDifficulty(LevelDifficulty.valueOf(line));
            }

            board = new Cell[getSize()][getSize()];

            for (int row = 0; row < getSize(); row++) {
                char[] rowChar = Objects.requireNonNull(getFirstNonEmptyLine(reader)).toCharArray();
                for (int column = 0; column < getSize(); column++) {
                    board[row][column] = CellFactory.getCellInstance(row, column,
                            Objects.requireNonNull(CellType.map2CellType(rowChar[column])));
                }
            }

        }catch (IllegalArgumentException | IOException e){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        //Check if there is one starting cell, one finish cell and, at least, any other type of cell.
        for(var j =0; j<getSize(); j++){

            if(getCell(new Coordinate(getSize()-1,j)).getType() == CellType.START){
                isStarting = true;
            }

            if(getCell(new Coordinate(0,j)).getType() == CellType.FINISH){
                isFinish = true;
            }
        }

        //Checks if there are more than one starting cell
        if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getType() == CellType.START).count()>1){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        //Checks if there are more than one finish cell
        if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getType() == CellType.FINISH).count()>1){
            throw new LevelException(LevelException.ERROR_PARSING_LEVEL_FILE);
        }

        if(!isStarting){
            throw new LevelException(LevelException.ERROR_NO_STARTING);
        }

        if(!isFinish){
            throw new LevelException(LevelException.ERROR_NO_FINISH);
        }

        //Checks if there is one road (i.e. movable or rotatable cell) at least.
        if(Stream.of(board).flatMap(Arrays::stream).noneMatch(x -> x.isMovable() || x.isRotatable())){
            throw new LevelException(LevelException.ERROR_NO_ROAD);
        }

    }

    /**
     * This a helper method for {@link #parse(String fileName)} which returns
     * the first non-empty and non-comment line from the reader.
     *
     * @param br BufferedReader object to read from.
     * @return First line that is a parsable line, or {@code null} there are no lines to read.
     * @throws IOException if the reader fails to read a line.
     */
    private String getFirstNonEmptyLine(final BufferedReader br) throws IOException {
        do {

            String s = br.readLine();

            if (s == null) {
                return null;
            }
            if (s.isBlank() || s.startsWith("#")) {
                continue;
            }

            return s;
        } while (true);
    }

    /**
     * Getter of the attribute "size".
     *
     * @return Value of the attribute "size".
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter of the attribute "size".
     *
     * @param size New value of the attribute "size".
     * @throws LevelException If the size is less than MINIMUM_BOARD_SIZE.
     */
    private void setSize(int size) throws LevelException {
        if (size < MINIMUM_BOARD_SIZE)
            throw new LevelException(LevelException.ERROR_BOARD_SIZE);

        this.size = size;
    }

    /**
     * Getter of the attribute "difficulty".
     * @return New value of the attribute "difficulty".
     */
    public LevelDifficulty getDifficulty(){
        return difficulty;
    }

    /**
     * Setter of the attribute "difficulty".
     * @param difficulty Difficulty of the level
     */
    private void setDifficulty(LevelDifficulty difficulty){
        this.difficulty = difficulty;
    }

    /**
     * Getter of the attribute "numMoves".
     *
     * @return Value of the attribute "numMoves".
     */
    public int getNumMoves() {
        return numMoves;
    }

    /**
     * Setter of the attribute "numMoves".
     *
     * @param numMoves New value of the attribute "numMoves".
     */
    private void setNumMoves(int numMoves) {
        this.numMoves = numMoves;
    }

    /**
     * Checks if the cell (row,column) is correct, i.e. it exists on the board.
     * Row in [0,SIZE) and column in [0,SIZE).
     *
     * @param coord Object with the info of row and column.
     * @return True if the cell (row,column) exists on the board. Otherwise, false.
     */
    private boolean validatePosition(Coordinate coord) {
        int row = coord.getRow();
        int column = coord.getColumn();

        if(coord == null)
            return false;

        return (row >= 0 && row < getSize()) && (column >= 0 && column < getSize());
    }

    /**
     * Returns the cell that is in the coordinate/position "coord".
     *
     * @param coord Coordinate/Position of the cell that we want to get.
     * @return The cell which is in the "coord" position in the board.
     * @throws LevelException If the coordinate is invalid.
     */
    public Cell getCell(Coordinate coord) throws LevelException{

        if(!validatePosition(coord))
            throw new LevelException(LevelException.ERROR_COORDINATE);

        return board[coord.getRow()][coord.getColumn()];
    }

    /**
     * If the coordinate is valid, then it sets the given cell in the board.
     *
     * @param coord Coordinate/Position of the cell that we want to get.
     * @param cell New cell which we want to set in the position "coord".
     * @throws LevelException When the coordinate is invalid.
     */
    private void setCell(Coordinate coord, Cell cell) throws LevelException{
        if(!validatePosition(coord)){
            throw new LevelException(LevelException.ERROR_COORDINATE);
        }

        board[coord.getRow()][coord.getColumn()] = cell;
    }

    /**
     * If it is possible, it swaps the cells in the given coordinates.<br/>
     * Moreover, if the swap is successful, then it increases the number of moves.
     *
     * @param firstCoord Coordinate/Position of the first cell that we want to swap.
     * @param secondCoord Coordinate/Position of the second cell that we want to swap.
     * @throws LevelException If the coordinate/position is invalid or the cell in that coordinate cannot be moved.
     */
    public void swapCells(Coordinate firstCoord, Coordinate secondCoord) throws LevelException{
        Cell firstCell = getCell(firstCoord);
        Cell secondCell = getCell(secondCoord);

        if (!firstCell.isMovable() || !secondCell.isMovable())
            throw new LevelException(LevelException.ERROR_NO_MOVABLE_CELL);

        setCell(secondCoord, firstCell);
        setCell(firstCoord, secondCell);

        ((MovableCell) secondCell).move(firstCoord);
        ((MovableCell) firstCell).move(secondCoord);

        setNumMoves(getNumMoves() + 1);
    }

    /**
     * Given the coordinate of a cell, it rotates that cell if it is possible.<br/>
     * Moreover, if the rotation is successful, then it increases the number of moves.
     *
     * @param coord Coordinate/Position of the cell that the player wants to rotate.
     * @throws LevelException If the coordinate/position is invalid or the cell in that coordinate cannot be rotated.
     */
    public void rotateCell(Coordinate coord) throws LevelException {

        Cell cell = getCell(coord);

        if (!cell.isRotatable())
            throw new LevelException(LevelException.ERROR_NO_ROTATABLE_CELL);

        ((RotatableCell) cell).rotate();

        setNumMoves(getNumMoves() + 1);
    }

    /**
     * Checks if the level is solved.
     *
     * @return True if the level is solved (i.e. there is a path from the starting cell to the finish cell). Otherwise, it returns false.
     * @throws LevelException If there is any problem while checking if the level is solved.
     */
    public boolean isSolved() throws LevelException {
        //We start with the "start" cell
        Optional<Cell> opt = Arrays.stream(board[getSize()-1]).filter(e -> e.getType() == CellType.START ).findFirst();

        if(opt.isPresent()){ //If the starting cell exists...
            Cell startingCell = opt.get();

            Coordinate newCoord = new Coordinate(startingCell.getCoordinate().getRow()+Direction.UP.getDRow(),
                    startingCell.getCoordinate().getColumn()+Direction.UP.getDColumn());

            //We know that the next cell after the starting cell only can be on
            // the top of the starting cell... so we start in such a top cell, and we say that we come from down (i.e. starting cell).
            return existsPath(getCell(newCoord), Direction.UP);
        }else{
            return false;
        }
    }

    /**
     * @hidden This is method that exists due to our algorithm.
     *
     * Helper recursive method for {@link #isSolved()} that follows the
     * current path from the starting cell. If a complete path is found,
     * then it returns true.
     *
     * @param currentCell Cell from which we look for a path.
     * @param comingFrom The previous cell in which we were.
     * @return True if there is a path from the starting cell to the ending cell. Otherwise, false.
     */
    private boolean existsPath(Cell currentCell,Direction comingFrom) {

        if(currentCell == null) return false;

        if(currentCell.getType().getAvailableConnections().contains(comingFrom.getOpposite())){

            if(currentCell.getType().equals((CellType.FINISH)))
                return true;
            else {
                EnumSet<Direction> connections = currentCell.getType().getAvailableConnections().clone();
                connections.remove(comingFrom.getOpposite());
                Direction pointingTo = (Direction) connections.stream().toArray()[0];
                Coordinate newCoord = new Coordinate(currentCell.getCoordinate().getRow()+pointingTo.getDRow(),
                        currentCell.getCoordinate().getColumn()+pointingTo.getDColumn());
                try {
                    return existsPath(getCell(newCoord),pointingTo);
                } catch (LevelException e) {
                    return false; //out of the board
                }
            }
        }else{
            return false;
        }
    }

    /**
     * Returns the board in textual format, i.e.:<br/>
     *   1234<br/>
     * a|····<br/>
     * b|····<br/>
     * @return String which contains the text-based board.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("  ");
        for (int i = 0; i < getSize(); i++)
            str.append(i + 1);

        str.append(System.lineSeparator());

        for (int row = 0; row < getSize(); row++) {
            str.append((char) (row + 97)).append("|");
            for (int column = 0; column < getSize(); column++) {
                str.append(board[row][column]);
            }
            str.append(System.lineSeparator());
        }

        return str.toString();
    }

}
