package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;

/**
 * Class that represents a cell that can be moved.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public class MovableCell extends Cell implements Movable {

    /**
     * Constructor
     *
     * @param row Row of the coordinate/position in which the cell is in the board.
     * @param column Column of the coordinate/position in which the cell is in the board.
     * @param type Relevant data of the cell according to the values of the enum CellType.
     */
    public MovableCell(int row, int column, CellType type) {
        super(row, column, type);
    }

    /**
     * Returns true.
     *
     * @return This is a movable cell, so it returns true.
     */
    @Override
    public boolean isMovable(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRotatable(){
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Coordinate destination) {
        setCoordinate(destination.getRow(),destination.getColumn());
    }
    
}
