import java.util.HashMap;

public class Cell {

    // Attributes ---------------------------------------------------------------------
    private int gridX;
    private int gridY;
    private int neighbourMineCount;
    private boolean hasMine;
    private boolean hasFlag;
    private boolean isRevealed;
    // --------------------------------------------------------------------------------

    // Constructor --------------------------------------------------------------------
    public Cell(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.neighbourMineCount = 0;
        this.hasMine = false;
        this.hasFlag = false;
        this.isRevealed = false;
    }
    // --------------------------------------------------------------------------------

    // Getters & Setters --------------------------------------------------------------
    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }

    public boolean getHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public boolean getIsRevealed() {
        return isRevealed;
    }

    public void setIsRevealed(boolean revealed) {
        isRevealed = revealed;
    }
    // --------------------------------------------------------------------------------
}
