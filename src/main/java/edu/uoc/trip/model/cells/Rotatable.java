package edu.uoc.trip.model.cells;

/**
 * This interfaces lists the methods that any cell which can be rotated must code.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public interface Rotatable {

    /**
     * Rotates the cell that invokes this method by using CellType's "next" method.
     */
    void rotate();
}
