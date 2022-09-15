package edu.uoc.trip.model.levels;

/**
 * Enumeration with the possible directions in which one can walk.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public enum Direction {
    UP(-1,0, 2),
    RIGHT(0, 1, 3),
    DOWN(1, 0, 0),
    LEFT(0, -1, 1)

    private final int dRow;
    private final int dColumn;
    private final int opposite;

    /**
     * Constructor with parameters. It sets the value of the homonym attributes.
     *
     * @param dRow The vertical direction (in index format) in which you have to "walk" through to get this direction.
     * @param dColumn The horizontal direction (in index format) in which you have to "walk" through to get this direction.
     * @param opposite The index of the direction which is opposite of the current direction.
     */
    Direction(int dRow, int dColumn, int opposite){
        this.dRow = dRow;
        this.dColumn = dColumn;
        this.opposite = opposite;
    }

    /**
     * Given an index, it returns the Direction which has that index.
     *
     * @param index Position of the direction which we want to get.
     * @return Returns the Direction which has the value of "index".
     */
    public static Direction getValueByIndex(int index){
        return Direction.values()[index];
    }

    /**
     * Getter of the attribute "dRow".
     *
     * @return Value of the attribute "dRow".
     */
    public int getDRow(){
        return dRow;
    }

    /**
     * Getter of the attribute "dColumn".
     *
     * @return Value of the attribute "dColumn".
     */
    public int getDColumn(){
        return dColumn;
    }

    /**
     * Returns the Direction value of the opposite direction of the current direction.
     *
     * @return Value of the opposite direction.
     */
    public Direction getOpposite(){
        return Direction.values()[opposite];
    }
}
