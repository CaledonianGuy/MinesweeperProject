public class Grid {

    // Attributes ---------------------------------------------------------------------
    int width;
    int height;
    int numMines;
    Cell[] cellList;
    // --------------------------------------------------------------------------------

    // Constructors -------------------------------------------------------------------
    public Grid() {
        this.width = 10;
        this.height = 8;
        this.numMines = 10;
        this.cellList = new Cell[this.width * this.height];
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.numMines = 10;
        this.cellList = new Cell[this.width * this.height];
    }
    // --------------------------------------------------------------------------------

    // Getters & Setters --------------------------------------------------------------
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public Cell[] getCellList() {
        return cellList;
    }

    public void setCellList(Cell[] cellList) {
        this.cellList = cellList;
    }
    // --------------------------------------------------------------------------------

    // Methods ------------------------------------------------------------------------
    public void buildGrid() {

    }

    // Could possibly return String
    public void drawGrid() {

    }

    public void updateGrid() {

    }

    // Could possibly return String
    public void drawMineMap() {

    }

    // Could these be in Cells???
    public void addMines() {

    }

    public void addFlag() {

    }

    public void removeFlag() {

    }
    // --------------------------------------------------------------------------------
}
