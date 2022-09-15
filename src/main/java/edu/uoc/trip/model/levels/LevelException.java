package edu.uoc.trip.model.levels;

/**
 * Class that represents an exception which is related to the Level class.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public class LevelException extends Exception {
    /**
     * Error message when the row index is incorrect.
     */
    public static final String ERROR_PARSING_LEVEL_FILE = "[ERROR] There was an error while loading the current level file!!";

    /**
     * Error message when the number of rows/cols for the current level is incorrect.
     */
    public static final String ERROR_BOARD_SIZE = "[ERROR] Board's size must be greater than 2!!";

    /**
     * Error message when the coordinate (row,column) is incorrect.
     */
    public static final String ERROR_COORDINATE = "[ERROR] This coordinate is incorrect!!";

    /**
     * Error message when there isn't starting cell.
     */
    public static final String ERROR_NO_STARTING = "[ERROR] This level does not have any starting cell!!";

    /**
     * Error message when there isn't finish cell.
     */
    public static final String ERROR_NO_FINISH = "[ERROR] This level does not have any finish cell!!";

    /**
     * Error message when there isn't, at least, one road cell.
     */
    public static final String ERROR_NO_ROAD = "[ERROR] This level does not have any road!!";

    /**
     * Error message when at least one of the cell is not movable.
     */
    public static final String ERROR_NO_MOVABLE_CELL = "[ERROR] You have chosen a static cell!!";

    /**
     * Error message when the cell cannot be rotated.
     */
    public static final String ERROR_NO_ROTATABLE_CELL = "[ERROR] You have chosen a non-rotatable cell!!";

    /**
     * Constructor with parameter
     *
     * @param msg This is the message you want to display when an LevelException is thrown.
     */
    public LevelException(String msg){
        super(msg);
    }
}
