public class Submarine extends Ship{
    /**
	 * Constructor of Cruiser
	 */
    public Submarine() {
        super(1);
    }

    @Override
    public int getLength(){
        return 1;
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}
