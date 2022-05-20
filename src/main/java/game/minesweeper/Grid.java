package game.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    // Attributes ---------------------------------------------------------------------
    private final int width;
    private final int height;
    private int numMines;
    private final Cell[][] mainGrid;
    private boolean hasHitMine;
    private final List<Cell> mineLocations;
    private String msgOutput;
    // --------------------------------------------------------------------------------

    // Constructors -------------------------------------------------------------------
    public Grid() {
        this.width = 10;
        this.height = 8;
        this.numMines = 10;
        this.mainGrid = new Cell[this.width][this.height];
        this.hasHitMine = false;
        this.mineLocations = new ArrayList<>();
        this.msgOutput = "";
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.numMines = 10;
        this.mainGrid = new Cell[this.width][this.height];
        this.hasHitMine = false;
        this.mineLocations = new ArrayList<>();
        this.msgOutput = "";
    }
    // --------------------------------------------------------------------------------

    // Getters & Setters --------------------------------------------------------------
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int numMines) {
        this.numMines = numMines;
    }

    public Cell getCell(int x, int y) {
        return mainGrid[x][y];
    }

    public boolean getIsHasHitMine() {
        return hasHitMine;
    }

    public List<Cell> getMineLocations() {
        return mineLocations;
    }

    public String getMsgOutput() {
        return msgOutput;
    }
    // --------------------------------------------------------------------------------

    // Methods ------------------------------------------------------------------------
    public void buildGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                mainGrid[col][row] = new Cell(col, row);
            }
        }
        addMines();
    }

    public String drawGrid() {
        StringBuilder msg = new StringBuilder("  ");
        for (int row = -1; row < height; row++) {
            if (row != -1) {
                msg.append(row + 1).append(" ");
            }

            for (int col = 0; col < width; col++) {
                if (row == -1) {
                    msg.append(col + 1);
                } else {
                    if (mainGrid[col][row].getHasFlag()) {
                        msg.append("F");
                    } else if (mainGrid[col][row].getIsRevealed()) {
                        if (mainGrid[col][row].getHasMine()) {
                            msg.append("M");
                        } else if (mainGrid[col][row].getNeighbourMineCount() > 0) {
                            msg.append(mainGrid[col][row].getNeighbourMineCount());
                        } else {
                            msg.append(" ");
                        }
                    } else {
                        msg.append("?");
                    }
                }

                if (col == width - 1) {
                    msg.append("\n");
                } else {
                    msg.append(" ");
                }
            }
        }

        return msg.toString();
    }

    public void updateGrid(int x, int y, String action) {
        if (isInRange(x - 1, y - 1)) {
            switch (action) {
                case "add" -> addFlag(x - 1, y - 1);
                case "remove" -> removeFlag(x - 1, y - 1);
                case "reveal" -> revealCell(x - 1, y - 1);
                default -> msgOutput = "Incorrect entry.";
            }
        } else {
            msgOutput = "Coordinates not in range.";
        }
    }

    public void addMines() {
        int minesToPlace = numMines;
        while (minesToPlace > 0) {
            int randomNumX = ThreadLocalRandom.current().nextInt(0, width);
            int randomNumY = ThreadLocalRandom.current().nextInt(0, height);

            if (!mainGrid[randomNumX][randomNumY].getHasMine()) {
                mainGrid[randomNumX][randomNumY].setHasMine(true);
                mineLocations.add(mainGrid[randomNumX][randomNumY]);
                for (Cell neighbour : getNeighbours(randomNumX, randomNumY)) {
                    neighbour.setNeighbourMineCount(neighbour.getNeighbourMineCount() + 1);
                }
                minesToPlace--;
            }
        }
    }

    public void addFlag(int x, int y) {
        if (mainGrid[x][y].getIsRevealed()) {
            msgOutput = "Cell already revealed.";
        } else if (!mainGrid[x][y].getHasFlag()) {
            mainGrid[x][y].setHasFlag(true);
            msgOutput = "Flag added.";
        } else {
            msgOutput = "There is already a flag.";
        }
    }

    public void removeFlag(int x, int y) {
        if (mainGrid[x][y].getIsRevealed()) {
            msgOutput = "Cell already revealed.";
        } else if (mainGrid[x][y].getHasFlag()) {
            mainGrid[x][y].setHasFlag(false);
            msgOutput = "Flag removed.";
        } else {
            msgOutput = "There is no flag to remove.";
        }
    }

    public void revealCell(int x, int y) {
        if (mainGrid[x][y].getHasMine()) {
            hasHitMine = true;
        } else {
            if (mainGrid[x][y].getHasFlag()) {
                mainGrid[x][y].setHasFlag(false);
            }

            if (!mainGrid[x][y].getIsRevealed()) {
                if (mainGrid[x][y].getNeighbourMineCount() > 0) {
                    mainGrid[x][y].setIsRevealed(true);
                } else {
                    mainGrid[x][y].setIsRevealed(true);
                    List<Cell> neighbours = getNeighbours(x, y);
                    for (Cell neighbour : neighbours) {
                        revealCell(neighbour.getGridX(), neighbour.getGridY());
                    }
                }
            }
        }
    }

    public List<Cell> getNeighbours(int x, int y) {
        List<Cell> neighbourList = new ArrayList<>();
        for (int row = y - 1; row < y + 2; row++) {
            for (int col = x - 1; col < x + 2; col++) {
                if (isInRange(col, row) && mainGrid[col][row] != mainGrid[x][y]) {
                    neighbourList.add(mainGrid[col][row]);
                }
            }
        }
        return neighbourList;
    }

    public boolean isInRange(int x, int y) {
        return (x > -1 && x < width) && (y > -1 && y < height);
    }

    public void revealAll() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (mainGrid[col][row].getHasFlag()) {
                    mainGrid[col][row].setHasFlag(false);
                }
                mainGrid[col][row].setIsRevealed(true);
            }
        }
    }
    // --------------------------------------------------------------------------------
}
