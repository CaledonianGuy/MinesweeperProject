import game.minesweeper.Cell;
import game.minesweeper.Grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
        Assertions.assertFalse(testGridOne.getHasHitMine(),
                "hasHitMine has not been made correctly in constructor Grid().");

        // Test Grid Two
        Assertions.assertFalse(testGridTwo.getHasHitMine(),
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

    @Test
    public void testDrawGrid() {
        smallGrid.setNumMines(0);
        smallGrid.buildGrid();

        // Setting-up flag
        smallGrid.getCell(0, 0).setHasFlag(true);

        // Setting-up mine
        smallGrid.getCell(1, 0).setIsRevealed(true);
        smallGrid.getCell(1, 0).setHasMine(true);

        // Setting-up mine count
        smallGrid.getCell(2, 0).setIsRevealed(true);
        smallGrid.getCell(2, 0).setNeighbourMineCount(1);

        // Setting-up reveal
        smallGrid.getCell(0, 1).setIsRevealed(true);

        // Setting-up test string
        String testString = """
                  1 2 3
                1 F M 1
                2   ? ?
                3 ? ? ?
                """;

        // Checking draw matches
        Assertions.assertEquals(testString, smallGrid.drawGrid(), "drawGrid not working correctly.");
    }

    @Test
    public void testUpdateGrid() {
        smallGrid.setNumMines(0);
        smallGrid.buildGrid();
        Cell testCell = smallGrid.getCell(0, 0);

        // Checking add
        smallGrid.updateGrid(testCell.getGridX() + 1, testCell.getGridY() + 1, "add");

        Assertions.assertTrue(testCell.getHasFlag(), "Add (switch) not working correctly.");


        // Checking remove
        smallGrid.updateGrid(testCell.getGridX() + 1, testCell.getGridY() + 1, "remove");

        Assertions.assertFalse(testCell.getHasFlag(), "Remove (switch) not working correctly.");


        // Checking reveal
        smallGrid.updateGrid(testCell.getGridX() + 1, testCell.getGridY() + 1, "reveal");

        Assertions.assertTrue(testCell.getIsRevealed(), "Reveal (switch) not working correctly.");


        // Checking incorrect entry
        smallGrid.updateGrid(testCell.getGridX() + 1, testCell.getGridY() + 1, "incorrect");

        Assertions.assertEquals("Incorrect entry.", smallGrid.getMsgOutput(),
                "Incorrect entry (switch) not working correctly.");


        // Checking out of range
        smallGrid.updateGrid(10, 10, "add");

        Assertions.assertEquals("Coordinates not in range.", smallGrid.getMsgOutput(),
                "Out of range not working correctly.");
    }

    @Test
    public void testAddFlag() {
        testGridOne.buildGrid();

        // Initial check
        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(),
                "getHasFlag not working correctly (initial check -- addFlag).");


        // Checking flag added
        testGridOne.addFlag(0, 0);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(),
                "addFlag is not working correctly (checking flag added).");
        Assertions.assertEquals("Flag added.", testGridOne.getMsgOutput(),
                "\"Flag added.\" message not working correctly.");


        // Checking flag cannot be added again
        testGridOne.addFlag(0, 0);

        Assertions.assertEquals("There is already a flag.", testGridOne.getMsgOutput(),
                "\"There is already a flag.\" message not working correctly.");


        // Checking for revealed cell
        testGridOne.getCell(0, 0).setHasFlag(false);
        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.addFlag(0, 0);

        Assertions.assertEquals("Cell already revealed.", testGridOne.getMsgOutput(),
                "\"Cell already revealed.\" message not working correctly.");
    }

    @Test
    public void testRemoveFlag() {
        testGridOne.buildGrid();

        // Initial check
        testGridOne.getCell(0, 0).setHasFlag(true);

        Assertions.assertTrue(testGridOne.getCell(0, 0).getHasFlag(),
                "getHasFlag not working correctly (initial check -- removeFlag).");


        // Checking flag removed
        testGridOne.removeFlag(0, 0);

        Assertions.assertFalse(testGridOne.getCell(0, 0).getHasFlag(),
                "removeFlag is not working correctly (checking flag removed).");
        Assertions.assertEquals("Flag removed.", testGridOne.getMsgOutput(),
                "\"Flag removed.\" message not working correctly.");


        // Checking flag cannot be removed again
        testGridOne.removeFlag(0, 0);

        Assertions.assertEquals("There is no flag to remove.", testGridOne.getMsgOutput(),
                "\"There is no flag to remove.\" message not working correctly.");


        // Checking for revealed cell
        testGridOne.getCell(0, 0).setIsRevealed(true);
        testGridOne.removeFlag(0, 0);

        Assertions.assertEquals("Cell already revealed.", testGridOne.getMsgOutput(),
                "\"Cell already revealed.\" message not working correctly.");
    }

    @Test
    public void testRevealCell() {
        smallGrid.setNumMines(1);
        smallGrid.buildGrid();
        Cell mineLoc = smallGrid.getMineLocations().get(0);

        // Initial check
        Assertions.assertFalse(smallGrid.getHasHitMine(),
                "hasHitMine has not working correctly (initial check -- reveal cell).");


        // Checking hit mine
        smallGrid.revealCell(mineLoc.getGridX(), mineLoc.getGridY());

        Assertions.assertTrue(smallGrid.getHasHitMine(),
                "revealCell not working correctly (checking hit mine).");


        // Checking flag removal
        mineLoc.setHasMine(false);
        mineLoc.setHasFlag(true);
        smallGrid.revealCell(mineLoc.getGridX(), mineLoc.getGridY());

        Assertions.assertFalse(mineLoc.getHasFlag(),
                "revealCell not working correctly (checking flag removal).");


        // Checking cell reveal
        Assertions.assertTrue(mineLoc.getIsRevealed(),
                "revealCell not working correctly (checking cell reveal).");


        // Checking neighbour reveal
        List<Cell> neighbours = smallGrid.getNeighbours(mineLoc.getGridX(), mineLoc.getGridY());
        Assertions.assertTrue(neighbours.get(0).getIsRevealed(),
                "revealCell not working correctly neighbour one (checking neighbour reveal).");
        Assertions.assertTrue(neighbours.get(1).getIsRevealed(),
                "revealCell not working correctly neighbour two (checking neighbour reveal).");
        Assertions.assertTrue(neighbours.get(2).getIsRevealed(),
                "revealCell not working correctly neighbour three (checking neighbour reveal).");


        // Checking mine count > 0
        mineLoc.setNeighbourMineCount(1);
        smallGrid.revealCell(mineLoc.getGridX(), mineLoc.getGridY());

        Assertions.assertTrue(mineLoc.getIsRevealed(),
                "revealCell not working correctly (checking mine count > 0).");
    }

    @Test
    public void testIsInRange() {
        Assertions.assertTrue(smallGrid.isInRange(1, 1), "inRange not working correctly (case one).");
        Assertions.assertFalse(smallGrid.isInRange(10, 1), "inRange not working correctly (case two).");
        Assertions.assertFalse(smallGrid.isInRange(1, 10), "inRange not working correctly (case three).");
        Assertions.assertFalse(smallGrid.isInRange(10, 10), "inRange not working correctly (case four).");
    }

    @Test
    public void testRevealAll() {
        smallGrid.setNumMines(0);
        smallGrid.buildGrid();
        Cell testCell = smallGrid.getCell(0, 0);

        // Initial check
        Assertions.assertFalse(testCell.getIsRevealed(),
                "isRevealed not working correctly (initial check -- reveal all).");


        // Checking flag removal
        smallGrid.getCell(0, 0).setHasFlag(true);
        smallGrid.revealAll();

        Assertions.assertTrue(testCell.getIsRevealed(),
                "revealAll not working correctly (checking flag removal).");

    }
}
