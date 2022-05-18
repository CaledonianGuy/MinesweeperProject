import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellTest {
    Cell testCell = new Cell(5, 6);

    @Test
    public void testSetCell() {
        Assertions.assertEquals(5, testCell.getGridX(), "Grid X has not been made correctly");
        Assertions.assertEquals(6, testCell.getGridY(), "Grid Y has not been made correctly");
        Assertions.assertEquals(0, testCell.getNeighbourMineCount(),
                "Neighbour Mine Count has not been made correctly");
        Assertions.assertFalse(testCell.getHasMine(), "Has Mine has not been made correctly");
        Assertions.assertFalse(testCell.getHasFlag(), "Has Flag has not been made correctly");
        Assertions.assertFalse(testCell.getIsRevealed(), "Is Revealed has not been made correctly");

        testCell.setGridX(10);
        testCell.setGridY(20);
        testCell.setNeighbourMineCount(2);
        testCell.setHasMine(true);
        testCell.setHasFlag(true);
        testCell.setIsRevealed(true);

        Assertions.assertEquals(10, testCell.getGridX(), "Grid X has not changed correctly");
        Assertions.assertEquals(20, testCell.getGridY(), "Grid Y has not changed correctly");
        Assertions.assertEquals(2, testCell.getNeighbourMineCount(),
                "Neighbour Mine Count has not changed correctly");
        Assertions.assertTrue(testCell.getHasMine(), "Has Mine has not changed correctly");
        Assertions.assertTrue(testCell.getHasFlag(), "Has Flag has not changed correctly");
        Assertions.assertTrue(testCell.getIsRevealed(), "Is Revealed has not changed correctly");
    }
}
