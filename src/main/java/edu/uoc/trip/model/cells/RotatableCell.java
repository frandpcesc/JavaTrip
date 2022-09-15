package edu.uoc.trip.model.cells;

/**
 * Class that represents a cell that can be rotated.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public class RotatableCell extends Cell implements Rotatable {

    /**
     * Constructor
     *
     * @param row Row of the coordinate/position in which the cell is in the board.
     * @param column Column of the coordinate/position in which the cell is in the board.
     * @param type Relevant data of the cell according to the values of the enum CellType.
     */
    public RotatableCell(int row, int column, CellType type) {
        super(row, column, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovable(){
        return false;
    }

    /**
     * Returns true.
     *
     * @return This is a rotatable cell, so it returns true.
     */
    @Override
    public boolean isRotatable(){
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rotate() {
        setType(getType().next());
    }
}
