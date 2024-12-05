import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipGameTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    void testAskToPlayYes() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("y\n".getBytes());
        System.setIn(inputStream);
        boolean result = BattleshipGame.askToPlay();
        assertTrue(result);
    }

    @Test
    void testAskToPlayNo() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("n\n".getBytes());
        System.setIn(inputStream);
        boolean result = BattleshipGame.askToPlay();
        assertFalse(result);
    }

    @Test
    void testAskToPlayInvalid() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("x\nn\n".getBytes());
        System.setIn(inputStream);
        boolean result = BattleshipGame.askToPlay();
        assertFalse(result);
        assertTrue(outputStream.toString().contains("Invalid input."));
    }

    @Test
    void testAcceptShotValidInputAndSunk() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("0\n0\n".getBytes());
        System.setIn(inputStream);

        Ocean ocean = new Ocean();
        Ship submarine = new Submarine();
        submarine.placeShipAt(0, 0, true, ocean);

        assertEquals("Submarine", ocean.getShipArray()[0][0].getShipType());
        BattleshipGame.acceptShot(ocean);

        assertTrue(outputStream.toString().contains("sunk"));
    }

    @Test
    void testAcceptShotValidInputAndHit() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("0\n0\n".getBytes());
        System.setIn(inputStream);

        Ocean ocean = new Ocean();
        Ship battleship = new Battleship();
        battleship.placeShipAt(0, 0, true, ocean);

        assertEquals("Battleship", ocean.getShipArray()[0][0].getShipType());
        BattleshipGame.acceptShot(ocean);

        assertTrue(outputStream.toString().contains("Hit"));
    }

    @Test
    void testAcceptShotInvalidInputandMiss() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("-1\n2\n10\n3\n".getBytes());
        System.setIn(inputStream);

        Ocean ocean = new Ocean();
        Ship battleship = new Battleship();
        battleship.placeShipAt(0, 0, true, ocean);

        BattleshipGame.acceptShot(ocean);

        assertTrue(outputStream.toString().contains("Invalid row"));
        assertTrue(outputStream.toString().contains("Invalid column"));
        assertTrue(outputStream.toString().contains("Miss"));
    }

}