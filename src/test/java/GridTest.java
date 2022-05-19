import game.minesweeper.Cell;
import game.minesweeper.Grid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GridTest {
    Grid testGridOne = new Grid();
    Grid testGridTwo = new Grid(10, 10);

    @Test
    public void testSetGetWidth() {
        // Test Grid One
        Assertions.assertEquals(10, testGridOne.getWidth(),
                "width has not been made correctly in constructor Grid().");

        // Test Grid Two
        Assertions.assertEquals(10, testGridTwo.getWidth(),
                "width has not been made correctly in constructor Grid(int, int).");
    }

    @Test
    public void testGetSetHeight() {
        // Test Grid One
        Assertions.assertEquals(8, testGridOne.getHeight(),
                "height has not been made correctly in constructor Grid().");

        // Test Grid Two
        Assertions.assertEquals(10, testGridTwo.getHeight(),
                "height has not been made correctly in constructor Grid(int, int).");
    }

    @Test
    public void testGetNumMines() {
        // Test Grid One
        Assertions.assertEquals(10, testGridOne.getNumMines(),
                "numMines has not been made correctly in constructor Grid().");

        testGridOne.setNumMines(5);

        Assertions.assertEquals(5, testGridOne.getNumMines(),
                "numMines has not changed correctly in constructor Grid().");

        // Test Grid Two
        Assertions.assertEquals(10, testGridTwo.getNumMines(),
                "numMines has not been made correctly in constructor Grid(int, int).");

        testGridTwo.setNumMines(5);

        Assertions.assertEquals(5, testGridTwo.getNumMines(),
                "numMines has not changed correctly in constructor Grid(int, int).");
    }

    @Test
    public void testGetSetHasHitMine() {
        // Test Grid One
        Assertions.assertFalse(testGridOne.getIsHasHitMine(),
                "hasHitMine has not been made correctly in constructor Grid().");

        // Test Grid Two
        Assertions.assertFalse(testGridTwo.getIsHasHitMine(),
                "hasHitMine has not been made correctly in constructor Grid(int, int).");
    }

    @Test
    public void testIsInRange() {

    }
}
