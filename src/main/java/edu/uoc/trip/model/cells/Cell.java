package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;

/**
 * Class that represents a static and non-rotatable cell of the board.
 * <br/>
 * Both movable cells and rotatable cells inherit from this class,
 * since these two classes are cells with special behaviour.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public class Cell {

    private Coordinate coordinate;
    private CellType type;

    /**
     * Constructor
     *
     * @param row Row of the coordinate/position in which the cell is in the board.
     * @param column Column of the coordinate/position in which the cell is in the board.
     * @param type Relevant data of the cell according to the values of the enum CellType.
     */
    public Cell(int row, int column, CellType type){
        setCoordinate(row, column);
        setType(type);
    }

    /**
     * Getter of the attribute "type".
     *
     * @return Value of the attribute "type".
     */
    public CellType getType(){
        return type;
    }

    /**
     * Setter of the attribute "type".
     *
     * @param type Type of the cell according to the values of the enum CellType.
     */
    protected void setType(CellType type){
        this.type = type;
    }

    /**
     * Getter of the attribute "coordinate".
     *
     * @return Value of the attribute "coordinate".
     */
    public Coordinate getCoordinate(){
        return coordinate;
    }

    /**
     * Setter of the attribute "coordinate".
     *
     * @param row Row of the coordinate/position in which the cell is in the board.
     * @param column Row of the coordinate/position in which the cell is in the board.
     */
    protected void setCoordinate(int row, int column){
        this.coordinate = new Coordinate(row,column);
    }

    /**
     * Returns true if the cell is movable. Otherwise, it returns false.
     * <br/>
     * By default, any cell is not movable, so this method returns false.
     *
     * @return false.
     */
    public boolean isMovable(){
        return false;
    }

    /**
     * Returns true if the cells is rotatable. Otherwise, it returns false.
     * <br/>
     * By default, any cell is not rotatable, so this method returns false.
     *
     * @return false.
     */
    public boolean isRotatable(){
        return false;
    }

    /**
     * Returns the unicode symbol of the cell according to the data stored in the attribute "type".
     *
     * @return Unicode symbol of the cell in String format.
     */
    @Override
    public String toString(){
        return String.valueOf(getType().getUnicodeRepresentation());
    }
}
