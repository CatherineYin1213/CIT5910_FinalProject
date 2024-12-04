public class Battleship extends Ship{
    /**
	 * Constructor of Battleship
     * Sets the inherited length variable and initializes the hit array,
     * based on the size of this ship (4 tiles).
	 */
    public Battleship() {
        super(4);
    }

    @Override
    public int getLength(){
        return 4;
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }
}
