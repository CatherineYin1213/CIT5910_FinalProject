import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OceanTest {
    private Ocean ocean;
    @BeforeEach
    void setUp() {
        ocean = new Ocean();
    }

    @Test
    void testIsOccupiedEmptySea() {
        assertFalse(ocean.isOccupied(0, 0));
    }

    @Test
    void testIsOccupiedWithShip() {
        Ship battleship = new Battleship();
        battleship.placeShipAt(0,0, true,ocean);
        assertTrue(ocean.isOccupied(0, 0));
        assertTrue(ocean.isOccupied(0, 1));
        assertTrue(ocean.isOccupied(0, 2));
        assertTrue(ocean.isOccupied(0, 3));
        assertFalse(ocean.isOccupied(0, 4));
    }

    @Test
    void testPlaceAllShipsRandomly() {
        ocean.placeAllShipsRandomly();
        int shipCount = 0;
        Ship[][] ships = ocean.getShipArray();
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (!(ships[row][col] instanceof EmptySea)) {
                    shipCount++;
                }
            }
        }
        assertEquals(20, shipCount);
    }

    @Test
    void testShootAtMiss() {
        boolean result = ocean.shootAt(0, 0);
        assertFalse(result);
        assertEquals(1, ocean.getShotsFired());
        assertEquals(0, ocean.getHitCount());
    }

    @Test
    void testShootAtHit() {
        Ship battleship = new Battleship();
        battleship.placeShipAt(0,0, true,ocean);
        boolean result = ocean.shootAt(0, 0);
        assertTrue(result);
        assertEquals(1, ocean.getShotsFired());
        assertEquals(1, ocean.getHitCount());
    }

    @Test
    void testShootAtSinkingShip() {
        Ship battleship = new Battleship();
        battleship.placeShipAt(0,0, true,ocean);
        ocean.shootAt(0, 0);
        ocean.shootAt(0, 1);
        ocean.shootAt(0, 2);
        ocean.shootAt(0, 3);
        assertTrue(battleship.isSunk());
        assertEquals(4, ocean.getShotsFired());
        assertEquals(4, ocean.getHitCount());
        assertEquals(1, ocean.getShipsSunk());
    }

    @Test
    void testIsGameOver() {
        ocean.placeAllShipsRandomly();
        Ship[][] ships = ocean.getShipArray();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if(!(ships[row][col] instanceof EmptySea)) {
                    ocean.shootAt(row, col);
                }
            }
        }
        assertTrue(ocean.isGameOver());
    }
}