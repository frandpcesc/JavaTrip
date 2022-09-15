package edu.uoc.trip.model.cells;

import edu.uoc.trip.model.levels.Direction;
import java.util.EnumSet;

/**
 *
 * This enum stores data which describe the different types of Cell.
 *
 * @author Francesc Dentí
 * @version 1.0
 */
public enum CellType {
    START('S', '\u005e', "start.png", true, false, false, false){
        public CellType next() {
            return null;
        }
    },
    FINISH('F', '\u0076', "finish.png", false, false, true, false){
        public CellType next() {
            return null;
        }
    },
    MOUNTAINS('M', '\u004d', "mountains.png", false, false, false, false){
        public CellType next() {
            return null;
        }
    },
    RIVER('~', '~', "river.png", false, false, false, false){
        public CellType next() {
            return null;
        }
    },
    VERTICAL('V', '\u2551', "road_vertical.png", true, false, true, false){
        public CellType next() {
            return CellType.HORIZONTAL;
        }
    },
    HORIZONTAL('H', '\u2550', "road_horizontal.png", false, true, false, true){
        public CellType next() {
            return CellType.VERTICAL;
        }
    },
    BOTTOM_RIGHT('r', '\u2554', "road_bottom_right.png", false, true, true, false){
        public CellType next() {
            return CellType.BOTTOM_LEFT;
        }
    },
    BOTTOM_LEFT('l', '\u2557', "road_bottom_left.png", false, false, true, true){
        public CellType next() {
            return CellType.TOP_LEFT;
        }
    },
    TOP_RIGHT('R', '\u255a', "road_top_right.png", true, true, false, false){
        public CellType next() {
            return CellType.BOTTOM_RIGHT;
        }
    },
    TOP_LEFT('L', '\u255D', "road_top_left.png", true, false, false, true){
        public CellType next() {
            return CellType.TOP_RIGHT;
        }
    },
    FREE('·', '\u00b7', "free.png", false, false, false,false) {
        public CellType next() {
            return null;
        }
    },
    ROTATABLE_VERTICAL('G', '┃', "road_rotatable_vertical.png", true, false, true, false){
        public CellType next() {
            return CellType.ROTATABLE_HORIZONTAL;
        }
    },
    ROTATABLE_HORIZONTAL('g', '\u2501', "road_rotatable_horizontal.png", false, true, false, true){
        public CellType next() {
            return CellType.ROTATABLE_VERTICAL;
        }
    };

    private char fileSymbol;
    private char unicodeRepresentation;
    private String imageSrc;
    private boolean[] connections;

    /**
     * Constructor
     *
     * @param fileSymbol Symbol that is used in the level configuration file.
     * @param unicodeRepresentation Unicode symbol that is used in the textual views.
     * @param imageSrc Name of the image that is used for GUI views.
     * @param connections Indicates where are the sides of the road.
     */
    CellType(char fileSymbol, char unicodeRepresentation, String imageSrc, boolean ...connections){
        setFileSymbol(fileSymbol);
        setUnicodeRepresentation(unicodeRepresentation);
        setImageSrc(imageSrc);
        setConnections(connections);
    }

    /**
     * Getter of the attribute "fileSymbol".
     *
     * @return Value of the attribute "fileSymbol".
     */
    public char getFileSymbol(){
        return fileSymbol;
    }

    /**
     * Setter of the attribute "fileSymbol".
     *
     * @param fileSymbol Symbol that will be used in the level configuration files.
     */
    private void setFileSymbol(char fileSymbol){
        this.fileSymbol = fileSymbol;
    }

    /**
     * Getter of the attribute "unicodeRepresentation".
     *
     * @return Value of the attribute "unicodeRepresentation".
     */
    public char getUnicodeRepresentation() {
        return unicodeRepresentation;
    }


    /**
     * Setter of the attribute "unicodeRepresentation".
     * @param unicodeRepresentation Unicode symbol that will be used in the textual views.
     */
    private void setUnicodeRepresentation(char unicodeRepresentation){
        this.unicodeRepresentation = unicodeRepresentation;
    }

    /**
     * Getter of the attribute "imageSrc".
     *
     * @return Value of the attribute "imageSrc".
     */
    public String getImageSrc() {
        return imageSrc;
    }

    /**
     * Setter of the attribute "imageSrc".
     *
     * @param imageSrc Name of the image that is used for GUI views.
     */
    private void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    /**
     * Setter of the attribute "connections".
     *
     * @param connections Sides of the path that is represented by this cell type.
     */
    private void setConnections(boolean[] connections) {
        this.connections = connections;
    }

    /**
     * Returns a EnumSet with the directions/sides in which the road exists,
     * e.g. {LEFT, RIGHT} for an HORIZONTAL road and {UP,DOWN} for a VERTICAL road.
     *
     * @return Set with the sides in which the road exists.
     */
    public EnumSet<Direction> getAvailableConnections(){
        EnumSet<Direction> availableConnections = EnumSet.noneOf(Direction.class);

        for(int i = 0; i<connections.length; i++){
            if(connections[i]) availableConnections.add(Direction.getValueByIndex(i));
        }

        return availableConnections;
    }

    /**
     * Given a Unicode character from a configuration file, it returns the CellType object.
     *
     * @param fileSymbol Symbol of the cell in the level/configuration file.
     * @return It returns the CellType of the cell from a Symbol level. If the Unicode character does not exist, then it returns null.
     */
    public static CellType map2CellType(char fileSymbol) {
        for(CellType ct : CellType.values()) {
            if(ct.getFileSymbol() == fileSymbol) {
                return ct;
            }
        }
        return null;
    }

    /**
     * This method is overridden by each CellType's value in order to say which is the next cell type
     * so that a clockwise order is established, e.g. bottom-right -> bottom-left.
     *
     * @return Next cell type for the cell type that invokes this method.
     */
    public abstract CellType next();

}
