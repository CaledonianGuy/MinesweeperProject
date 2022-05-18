import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    // Attributes ---------------------------------------------------------------------
    int width;
    int height;
    int numMines;
    Cell[][] grid;
    // --------------------------------------------------------------------------------

    // Constructors -------------------------------------------------------------------
    public Grid() {
        this.width = 10;
        this.height = 8;
        this.numMines = 10;
        this.grid = new Cell[this.width][this.height];
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.numMines = 10;
        this.grid = new Cell[this.width][this.height];
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

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }
    // --------------------------------------------------------------------------------

    // Methods ------------------------------------------------------------------------
    public void buildGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[col][row] = new Cell(col, row);
                if (col == row) {
                    grid[col][row].setIsRevealed(true);
                }
            }
        }
    }

    public String drawGrid() {
        StringBuilder msg = new StringBuilder("  ");
        for (int i = 0; i < width; i++) {
            msg.append(i + 1).append(" ");
        }
        msg.append("\n");
        for (int row = 0; row < height; row++) {
            msg.append(row + 1).append(" ");
            for (int col = 0; col < width; col++) {
                if (!grid[col][row].getIsRevealed() && col == width - 1) {
                    msg.append(" \n");
                } else if (!grid[col][row].getIsRevealed()) {
                    msg.append("  ");
                } else if (grid[col][row].getIsRevealed() && col == width - 1) {
                    msg.append(col + 1).append("\n");
                } else {
                    msg.append(col + 1).append(" ");
                }
            }
        }
        return msg.toString();
    }

    public void updateGrid() {

    }

    public String drawMineMap() {
        StringBuilder msg = new StringBuilder("  ");
        for (int i = 0; i < width; i++) {
            msg.append(i + 1).append(" ");
        }
        msg.append("\n");
        for (int row = 0; row < height; row++) {
            msg.append(row + 1).append(" ");
            for (int col = 0; col < width; col++) {
                if (!grid[col][row].getHasMine() && col == width - 1) {
                    msg.append(" \n");
                } else if (!grid[col][row].getHasMine()) {
                    msg.append("  ");
                } else if (grid[col][row].getHasMine() && col == width - 1) {
                    msg.append("M\n");
                } else {
                    msg.append("M ");
                }
            }
        }
        return msg.toString();
    }

    public void addMines() {
        int minesToPlace = numMines;
        while (minesToPlace > 0) {
//            int randomNumX = ThreadLocalRandom.current().nextInt(0, width + 1);
//            int randomNumY = ThreadLocalRandom.current().nextInt(0, height + 1);

            int randomNumX = (int) (Math.random() * (((width - 1)) + 1));
            int randomNumY = (int) (Math.random() * (((height - 1)) + 1));

            if (!grid[randomNumX][randomNumY].getHasMine()) {
                grid[randomNumX][randomNumY].setHasMine(true);
                minesToPlace--;
            }
        }
    }

    public void addFlag(int x, int y) {
        // Might need to check if the cell is revealed too.
        if (!grid[x][y].getHasFlag()) {
            grid[x][y].setHasFlag(true);
        } else {
            // This might need so extra things in main
            System.out.println("There is already a flag.");
        }
    }

    public void removeFlag(int x, int y) {
        if (grid[x][y].getHasFlag()) {
            grid[x][y].setHasFlag(false);
        } else {
            // This might need so extra things in main
            System.out.println("There is no flag to remove.");
        }
    }
    // --------------------------------------------------------------------------------
}
