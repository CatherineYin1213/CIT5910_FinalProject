public class Destroyer extends Ship{
    /**
	 * Constructor of Destroyer
	 */
    public Destroyer() {
        super(2);
    }

    @Override
    public int getLength(){
        return 2;
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }
}
