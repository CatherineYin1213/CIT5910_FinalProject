public class EmptySea extends Ship{
    /**
	 * Constructor of EmptySea
     * Sets the inherited length variable and initializes the hit array,
     * based on the size of this Empty Sea (1 tiles).
	 */
    public EmptySea() {
        super(1);
    }

    /**
	 * @param row - the row of the shot
     * @param column - the column of the shot
     * @return false always, since nothing will be hit.
	 */
    @Override
    public boolean shootAt(int row, int column) {
        hit[0] = true;
        return false;
    }

    /**
     * @return false always, since nothing will be hit.
	 */
    @Override
    public boolean isSunk(){
        return false;
    }

    @Override
    public int getLength(){
        return 1;
    }

    @Override
    public String getShipType() {
        return "empty";
    }

    @Override
    public String toString() {
        return hit[0] ? "-" : ".";
    }
}
