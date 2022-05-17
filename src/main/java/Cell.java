public class Cell {

    // Attributes ---------------------------------------------------------------------
    int[] pos;
    int neighbourMineCount;
    boolean hasMine;
    boolean hasFlag;
    Cell[] neighbours;
    // --------------------------------------------------------------------------------

    // Constructor --------------------------------------------------------------------
    public Cell(int[] pos) {
        this.pos = pos;
        this.neighbourMineCount = 0;
        this.hasMine = false;
        this.hasFlag = false;
        this.neighbours = new Cell[8];
    }
    // --------------------------------------------------------------------------------

    // Getters & Setters --------------------------------------------------------------
    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    public int getNeighbourMineCount() {
        return neighbourMineCount;
    }

    public void setNeighbourMineCount(int neighbourMineCount) {
        this.neighbourMineCount = neighbourMineCount;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean isHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public Cell[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Cell[] neighbours) {
        this.neighbours = neighbours;
    }
    // --------------------------------------------------------------------------------
}
