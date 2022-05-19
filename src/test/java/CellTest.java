import game.minesweeper.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellTest {
    Cell testCell = new Cell(5, 6);

    @Test
    public void testGetSetGridX() {
        Assertions.assertEquals(5, testCell.getGridX(),
                "gridX has not been made correctly");
    }

    @Test
    public void testGetSetGridY() {
        Assertions.assertEquals(6, testCell.getGridY(),
                "gridY has not been made correctly");
    }

    @Test
    public void testGetSetNeighbourMineCount() {
        Assertions.assertEquals(0, testCell.getNeighbourMineCount(),
                "neighbour Mine Count has not been made correctly");

        testCell.setNeighbourMineCount(2);

        Assertions.assertEquals(2, testCell.getNeighbourMineCount(),
                "neighbour Mine Count has not changed correctly");
    }

    @Test
    public void testGetSetHasMine() {
        Assertions.assertFalse(testCell.getHasMine(), "hasMine has not been made correctly");

        testCell.setHasMine(true);

        Assertions.assertTrue(testCell.getHasMine(), "hasMine has not changed correctly");
    }

    @Test
    public void testGetSetHasFlag() {
        Assertions.assertFalse(testCell.getHasFlag(), "hasFlag has not been made correctly");

        testCell.setHasFlag(true);

        Assertions.assertTrue(testCell.getHasFlag(), "hasFlag has not changed correctly");
    }

    @Test
    public void testGetSetIsRevealed() {
        Assertions.assertFalse(testCell.getIsRevealed(), "isRevealed has not been made correctly");

        testCell.setIsRevealed(true);

        Assertions.assertTrue(testCell.getIsRevealed(), "isRevealed has not changed correctly");
    }
}
