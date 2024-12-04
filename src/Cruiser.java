public class Cruiser extends Ship{
    /**
	 * Constructor of Cruiser
	 */
    public Cruiser() {
        super(3);
    }

    @Override
    public int getLength(){
        return 3;
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}
