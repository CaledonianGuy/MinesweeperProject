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
    Grid testGridOne = new Grid();
    Grid testGridTwo = new Grid(10, 10);
    Grid smallGrid = new Grid(3, 3);

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
        Grid testGridThree = new Grid();

        // Test Grid One
        // Check getCell
        Assertions.assertNull(testGridThree.getCell(0, 0),
                "getCell is not working correctly.");

        // Check getMineLocations
        Assertions.assertEquals(testList, testGridThree.getMineLocations(),
                "getMineLocations not working correctly.");

        // Build the grid
        testGridThree.buildGrid();

        // Check buildGrid
        Assertions.assertNotNull(testGridThree.getCell(0, 0),
                "buildGrid is not working correctly.");

        // Check addMines
        Assertions.assertNotEquals(testList, testGridThree.getMineLocations(),
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
    public void testAddFlag() {
        testGridOne.buildGrid();

        // Test Grid One
        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(), "");

        testGridOne.addFlag(0, 0);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(), "");
        Assertions.assertEquals("Flag added.", testGridOne.getMsgOutput(), "");

        testGridOne.addFlag(0, 0);

        Assertions.assertEquals("There is already a flag.", testGridOne.getMsgOutput(), "");

        testGridOne.getCell(0, 0).setHasFlag(false);
        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.addFlag(0, 0);

        Assertions.assertEquals("Cell already revealed.", testGridOne.getMsgOutput(), "");
    }

    @Test
    public void testRemoveFlag() {
        testGridOne.buildGrid();

        testGridOne.getCell(0, 0).setHasFlag(true);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(), "");

        testGridOne.removeFlag(0, 0);

        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(), "");
        Assertions.assertEquals("Flag removed.", testGridOne.getMsgOutput(), "");

        testGridOne.removeFlag(0, 0);

        Assertions.assertEquals("There is no flag to remove.", testGridOne.getMsgOutput(), "");

        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.removeFlag(0, 0);

        Assertions.assertEquals("Cell already revealed.", testGridOne.getMsgOutput(), "");
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
