import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    // Attributes ---------------------------------------------------------------------
    private int width;
    private int height;
    private int numMines;
    private Cell[][] grid;
    private boolean hasHitMine;
    // --------------------------------------------------------------------------------

    // Constructors -------------------------------------------------------------------
    public Grid() {
        this.width = 10;
        this.height = 8;
        this.numMines = 10;
        this.grid = new Cell[this.width][this.height];
        this.hasHitMine = false;
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.numMines = 10;
        this.grid = new Cell[this.width][this.height];
        this.hasHitMine = false;
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

    public boolean isHasHitMine() {
        return hasHitMine;
    }

    public void setHasHitMine(boolean hasHitMine) {
        this.hasHitMine = hasHitMine;
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
        addMines();
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

    public void updateGrid(int x, int y, String action) {
        switch (action) {
            case "+":
                addFlag(x, y);
                break;
            case "-":
                removeFlag(x, y);
                break;
            case "R":
                revealCell(x, y);
                break;
            default:
                break;
        }
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

    private void addMines() {
        int minesToPlace = numMines;
        while (minesToPlace > 0) {
            int randomNumX = (int) (Math.random() * (((width - 1)) + 1));
            int randomNumY = (int) (Math.random() * (((height - 1)) + 1));

            if (!grid[randomNumX][randomNumY].getHasMine()) {
                grid[randomNumX][randomNumY].setHasMine(true);
                minesToPlace--;
            }
        }
    }

    private void addFlag(int x, int y) {
        // Might need to check if the cell is revealed too.
        if (!grid[x][y].getHasFlag()) {
            grid[x][y].setHasFlag(true);
        } else {
            // This might need so extra things in main
            System.out.println("There is already a flag.");
        }
    }

    private void removeFlag(int x, int y) {
        if (grid[x][y].getHasFlag()) {
            grid[x][y].setHasFlag(false);
        } else {
            // This might need so extra things in main
            System.out.println("There is no flag to remove.");
        }
    }

    private void revealCell(int x, int y) {
        if (grid[x][y].getHasMine()) {
            hasHitMine = true;
        } else {
            if (grid[x][y].getNeighbourMineCount() > 0) {
                grid[x][y].setIsRevealed(true);
            } else {
                grid[x][y].setIsRevealed(true);
                List<Cell> neighbours = getNeighbours(x, y);
                for (Cell neighbour : neighbours) {
                    revealCell(neighbour.getGridX(), neighbour.getGridY());
                }
            }
        }
    }

    private List<Cell> getNeighbours(int x, int y) {
        List<Cell> neighbourList = new ArrayList<>();
        for (int row = y - 1; row < x + 2; row++) {
            for (int col = x - 1; col < x + 2; col++) {
                if (col != x && row != y) {
                    if ((col > -1 && col < width) && (row > -1 && row < height)) {
                        neighbourList.add(grid[x][y]);
                    }
                }
            }
        }
        return neighbourList;
    }
    // --------------------------------------------------------------------------------
}
