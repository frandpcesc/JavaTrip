package edu.uoc.trip.model.utils;

import java.util.Objects;

/**
 * Class that represents a coordinate/position in the board of the game.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public class Coordinate {

    private int row;
    private int column;

    /**
     * Constructor with parameters. It sets the value of the row and column attributes.
     *
     * @param row Position related to the row (y-axis).
     * @param column Position related to the column (x-axis).
     */
    public Coordinate(int row, int column){
        setRow(row);
        setColumn(column);
    }

    /**
     * Getter of the attribute "row".
     *
     * @return Value of the field "row".
     */
    public int getRow() {
        return row;
    }

    /**
     * Setter of the attribute "row".
     *
     * @param row New position related to the row (y-axis).
     */
    private void setRow(int row) {
        this.row = row;

    }

    /**
     * Getter of the attribute "column".
     *
     * @return Value of the field "column".
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter of the attribute "column".
     *
     * @param column New position related to the column (x-axis).
     */
    private void setColumn(int column){
        this.column = column;
    }

    /**
     * {@inheritDoc}
     * It also returns true if the rows and columns match.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Coordinate)) return false;

        Coordinate otherCoordinate = (Coordinate) obj;
        return row == otherCoordinate.row && column == otherCoordinate.column;
    }

    /**
     * When Object's method is overridden, hasCode method is also overridden much often.
     * <br/>
     * This is overridden by using "Objects.hash" method and its values of row and column.
     *
     * @return Coordinate object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }

    /**
     * Returns a String with the pattern: (row,column).
     *
     * @return Coordinate in textual format.
     */
    @Override
    public String toString() {
        return "(" + getRow() + "," + getColumn() + ")";
    }
}
