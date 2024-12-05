import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipSubclassTest {
    private Ocean ocean;

    @BeforeEach
    void setUp() {
        ocean = new Ocean();
    }

    @Test
    void testBattleship() {
        Ship battleShipe = new Battleship();
        assertEquals(4, battleShipe.getLength());
        assertEquals("Battleship", battleShipe.getShipType());

        assertFalse(battleShipe.okToPlaceShipAt(0, 7, true, ocean));
        assertTrue(battleShipe.okToPlaceShipAt(0, 0, true, ocean));
        battleShipe.placeShipAt(0,0, true, ocean);
        assertFalse(battleShipe.shootAt(1, 0)); //shoot the empty sea
        assertTrue(battleShipe.shootAt(0, 0));
        assertTrue(battleShipe.shootAt(0, 2));
        //if hit 2 tiles of the battleShip, it does not sink
        assertFalse(battleShipe.isSunk());
        assertTrue(battleShipe.shootAt(0, 1));
        assertTrue(battleShipe.shootAt(0, 3));
        //if hit 4 tiles of the battleShip, it should sink
        assertTrue(battleShipe.isSunk());
        //if the battleShip is already sunk, shootAt should return false
        assertFalse(battleShipe.shootAt(0, 0));
    }

    @Test
    void testCruiser() {
        Ship cruiser = new Cruiser();
        assertEquals(3, cruiser.getLength());
        assertEquals("Cruiser", cruiser.getShipType());
        assertFalse(cruiser.okToPlaceShipAt(9, 0, false, ocean));
        assertTrue(cruiser.okToPlaceShipAt(7, 0, false, ocean));
        cruiser.placeShipAt(7,0, false, ocean);
        assertTrue(cruiser.shootAt(7, 0));
        assertFalse(cruiser.shootAt(7, 1)); //shoot the empty sea
        assertFalse(cruiser.isSunk());
        assertTrue(cruiser.shootAt(8, 0));
        assertTrue(cruiser.shootAt(9,0));
        //if hit 3 tiles of the cruiser, it should sink
        assertTrue(cruiser.isSunk());
        //if the cruiser is already sunk, shootAt should return false
        assertFalse(cruiser.shootAt(7,0));
    }

    @Test
    void testDestoryer() {
        Ship destoryer = new Destroyer();
        assertEquals(2, destoryer.getLength());
        assertEquals("Destroyer", destoryer.getShipType());
        assertFalse(destoryer.okToPlaceShipAt(9, 0, false, ocean));
        assertTrue(destoryer.okToPlaceShipAt(0, 0, true, ocean));
        destoryer.placeShipAt(0,0, true, ocean);
        assertFalse(destoryer.shootAt(1,0)); //shoot the empty sea
        assertTrue(destoryer.shootAt(0, 0));
        assertFalse(destoryer.isSunk());
        assertTrue(destoryer.shootAt(0, 1));
        assertTrue(destoryer.isSunk());
        assertFalse(destoryer.shootAt(0,0));
    }

    @Test
    void testSubmarine() {
        Ship submarine = new Submarine();
        assertEquals(1, submarine.getLength());
        assertEquals("Submarine", submarine.getShipType());
        assertTrue(submarine.okToPlaceShipAt(1, 1, false, ocean));
        submarine.placeShipAt(1, 1, false,ocean);
        assertTrue(submarine.shootAt(1, 1));
        assertTrue(submarine.isSunk());
        assertFalse(submarine.shootAt(1,1));
    }

    @Test
    void testEmptySea() {
        Ship emptySea = new EmptySea();
        assertEquals(1, emptySea.getLength());
        assertFalse(emptySea.hit[0]);
        assertEquals("empty", emptySea.getShipType());
        assertEquals(".", emptySea.toString());
        assertFalse(emptySea.shootAt(0,0));
        assertTrue(emptySea.hit[0]);
        assertEquals("-", emptySea.toString());
        assertFalse(emptySea.isSunk());
    }
}