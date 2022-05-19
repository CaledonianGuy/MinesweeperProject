import game.minesweeper.Cell;
import game.minesweeper.Grid;
import junit.framework.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class GridTest {
    private final PrintStream standardOut = System.out;
    private  final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    Grid testGridOne = new Grid();
    Grid testGridTwo = new Grid(10, 10);
    Grid smallGrid = new Grid(3, 3);

    @BeforeEach
    public void setUp() {
        System.setOut((new PrintStream((outputStreamCaptor))));
    }

    @AfterEach
    public void tearDown() {
        System.setOut((standardOut));
    }

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
    public void testBuildGrid() {
        List<Cell> testList = new ArrayList<>();

        // Test Grid One
        // Check getCell
        Assertions.assertNull(testGridOne.getCell(0, 0),
                "getCell is not working correctly.");

        // Check getMineLocations
        Assertions.assertEquals(testList, testGridOne.getMineLocations(),
                "getMineLocations not working correctly.");

        // Build the grid
        testGridOne.buildGrid();

        // Check buildGrid
        Assertions.assertNotNull(testGridOne.getCell(0, 0),
                "buildGrid is not working correctly.");

        // Check addMines
        Assertions.assertNotEquals(testList, testGridOne.getMineLocations(),
                "addMine not working correctly.");
    }

    /*
    @Test
    public void testDrawGrid() {
        Grid smallGrid = new Grid(3, 3);
        smallGrid.setNumMines(1);
    }

     */

    @Test
    public void testUpdateGrid() {

    }

    @Test
    public void testAddFlagOne() {
        testGridOne.buildGrid();

        // Test Grid
        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(), "");

        testGridOne.addFlag(0, 0);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(), "");
        Assert.assertEquals("Flag added.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testAddFlagTwo() {
        testGridOne.buildGrid();

        testGridOne.getCell(0, 0).setHasFlag(true);
        testGridOne.addFlag(0, 0);

        Assert.assertEquals("There is already a flag.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testAddFlagThree() {
        testGridOne.buildGrid();

        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.addFlag(0, 0);

        Assert.assertEquals("Cell already revealed.", outputStreamCaptor.toString().trim());
    }


    @Test
    public void testRemoveFlagOne() {
        testGridOne.buildGrid();

        testGridOne.getCell(0, 0).setHasFlag(true);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(), "");

        testGridOne.removeFlag(0, 0);

        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(), "");
        Assert.assertEquals("Flag removed.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testRemoveFlagTw0() {
        testGridOne.buildGrid();

        testGridOne.removeFlag(0, 0);

        Assert.assertEquals("There is no flag to remove.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testRemoveFlagThree() {
        testGridOne.buildGrid();

        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.removeFlag(0, 0);

        Assert.assertEquals("Cell already revealed.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testRevealCell() {
        smallGrid.setNumMines(1);
        smallGrid.buildGrid();
        Cell mineLoc = smallGrid.getMineLocations().get(0);

        Assertions.assertFalse(smallGrid.getIsHasHitMine(), "");

        smallGrid.revealCell(mineLoc.getGridX(), mineLoc.getGridY());

        Assertions.assertTrue(smallGrid.getIsHasHitMine(), "");
    }

    @Test
    public void testGetNeighbours() {

    }

    @Test
    public void testIsInRange() {
        Assertions.assertTrue(smallGrid.isInRange(1, 1), "");
        Assertions.assertFalse(smallGrid.isInRange(10, 1), "");
        Assertions.assertFalse(smallGrid.isInRange(1, 10), "");
        Assertions.assertFalse(smallGrid.isInRange(10, 10), "");
    }

    @Test
    public void testRevealAll() {
        smallGrid.setNumMines(0);
        smallGrid.buildGrid();

        Assertions.assertFalse(smallGrid.getCell(0, 0).getIsRevealed(), "");

        smallGrid.revealAll();

        Assertions.assertTrue(smallGrid.getCell(0, 0).getIsRevealed(), "");

    }
}
