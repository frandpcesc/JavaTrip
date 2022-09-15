package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.utils.Coordinate;

/**
 * This interfaces lists the methods that any cell which can be moved must code.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public interface Movable {
    /**
     * Moves the cell that invokes this method from the current position/coordinate to the destination coordinate/position.
     * @param destination Position of the board in which we want to move the cell that invokes this method.
     */
    void move(Coordinate destination);
}
