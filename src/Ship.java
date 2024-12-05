public abstract class Ship {
    /**
	 * The column (0 to 9) which contains the bow (front) of the ship.
	 */
    protected int bowColumn;

    /**
	 * The row (0 to 9) which contains the bow (front) of the ship.
	 */
    protected int bowRow;

    /**
	 * hit is an array of four booleans telling whether that part of the ship has been hit.
     * Only battleships will use all four locations;
     * cruisers use only the first three,
     * destroyers the first two,
     * submarines and empty sea one.
	 */
    protected boolean[] hit;

    /**
	 * true if the ship occupies a single row, false otherwise.
	 */
    protected boolean horizontal;

    /**
	 * The number of tiles occupied by the ship.
     * Empty sea locations have a length of 1.
	 */
    protected int length;



    /**
	 * Constructor of Ship
	 */
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    /**
	 * the length of the ship. Abstract function.
	 */
    public abstract int getLength();

    /**
	 * the row of the bow (front) of the ship
	 */
    public int getBowRow() {
        return bowRow;
    }

    /**
	 * the column of the bow (front) of the ship
	 */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
	 * bowColumn - the bowColumn to set
	 */
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    /**
	 * bowRow - the bowRow to set
	 */
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    /**
	 * true if this boat is horizontal (facing left), false otherwise.
	 */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
	 * horizontal - the horizontal to set
	 */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
	 * the String representing the type of this ship.
     * Abstract function
	 */
    public abstract String getShipType();


    /**
	 * Determines whether or not this is represents a valid placement configuration for this Ship in this Ocean.
     * Ship objects in an Ocean must not overlap other Ship objects or touch them vertically, horizontally, or diagonally.
     * Additionally, the placement cannot be such that the Ship would extend beyond the extents of the 2D array in which it is placed.
     * Calling this method should not actually change either the Ship or the provided Ocean.
     * @param row - the candidate row to place the ship
     * @param column - the candidate column to place the ship
     * @param horizontal - whether or not to have the ship facing to the left
     * @param ocean - the Ocean in which this ship might be placed
     * @return {@literal true} if it is valid to place this ship of this length in this location with this orientation
     *          {@literal false} otherwise.
	 */
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		int length = this.getLength();
        //check if the ship would go out of bounds
        if (horizontal) {
            if (column + length > 10) return false;
        } else {
            if (row + length > 10) return false;
        }

        //check the surroundings of the ship for overlap or adjacency
        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? row : row + i;
            int currentCol = horizontal ? column + i : column;
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int checkRow = currentRow + dr;
                    int checkCol = currentCol + dc;
                    if (checkRow >= 0 && checkRow < 10 && checkCol >= 0 && checkCol < 10) {
                        // If any surrounding cell is not EmptySea, placement is invalid
                        if (!(ocean.ships[checkRow][checkCol] instanceof  EmptySea)) return false;
                    }
                }
            }
        }
        return true;
	}

    /**
     * his will give values to the bowRow, bowColumn, and horizontal instance variables in the Ship.
     * This should also place a reference to this Ship in each of the one or more locations (up to four) in the corresponding Ocean array this Ship is being placed in.
	 * @param row - the row to place the ship
     * @param column - the column to place the ship
     * @param horizontal - whether or not to have the ship facing to the left
     * @param ocean - the Ocean in which this ship will be placed
	 */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        int length = this.getLength();
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? row : row + i;
            int currentCol = horizontal ? column + i : column;
            ocean.ships[currentRow][currentCol] = this;
        }
    }

    /**
	 * @param row - the row of the shot
     * @param column - the column of the shot
     * @return {@literal true} if this ship hasn't been sunk and a part of this ship occupies the given row and column
     *          {@literal false} otherwise.
	 */
    public boolean shootAt(int row, int column) {
        if(isSunk()) return false;
        int length = this.getLength();
        for (int i = 0; i < length; i++) {
            int currentRow = horizontal ? bowRow : bowRow + i;
            int currentCol = horizontal ? bowColumn + i : bowColumn;
            if(currentRow == row && currentCol == column) {
                hit[i] = true;
                return true;
            }
        }
        return false;
    }

    /**
	 * @return {@literal true} if every part of the ship has been hit
     *          {@literal false}otherwise.
	 */
    public boolean isSunk() {
        int length = this.getLength();
        for (int i = 0; i < length; i++) {
            if(!hit[i]) return false;
        }
        return true;
    }

    /**
     * return "x" if the ship has been sunk, and "S" if it has not yet been sunk.
	 */
    public String toString() {
        if(isSunk()) return "x".repeat(getLength());
        StringBuilder result = new StringBuilder();
        for (boolean partHit : hit) {
            if (partHit) {
                result.append('S');
            } else{
                result.append('.');
            }
        }
        return result.toString();
    }
}
