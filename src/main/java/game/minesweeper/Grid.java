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
    // --------------------------------------------------------------------------------

    // Constructors -------------------------------------------------------------------
    public Grid() {
        this.width = 10;
        this.height = 8;
        this.numMines = 10;
        this.mainGrid = new Cell[this.width][this.height];
        this.hasHitMine = false;
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.numMines = 10;
        this.mainGrid = new Cell[this.width][this.height];
        this.hasHitMine = false;
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

    public boolean getIsHasHitMine() {
        return hasHitMine;
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
        for (int i = 0; i < width; i++) {
            msg.append(i + 1).append(" ");
        }
        msg.append("\n");
        for (int row = 0; row < height; row++) {
            msg.append(row + 1).append(" ");
            for (int col = 0; col < width; col++) {
                if (!mainGrid[col][row].getIsRevealed()) {
                    if (col == width - 1) {
                        msg.append("?\n");
                    } else {
                        msg.append("? ");
                    }
                } else {
                    if (mainGrid[col][row].getHasFlag()) {
                        if (col == width - 1) {
                            msg.append("F").append("\n");
                        } else {
                            msg.append("F").append(" ");
                        }
                    } else if (mainGrid[col][row].getHasMine()) {
                        if (col == width - 1) {
                            msg.append("M").append("\n");
                        } else {
                            msg.append("M").append(" ");
                        }
                    } else {
                        if (mainGrid[col][row].getNeighbourMineCount() == 0) {
                            if (col == width - 1) {
                                msg.append(" ").append("\n");
                            } else {
                                msg.append("  ");
                            }
                        } else {
                            if (col == width - 1) {
                                msg.append(mainGrid[col][row].getNeighbourMineCount()).append("\n");
                            } else {
                                msg.append(mainGrid[col][row].getNeighbourMineCount()).append(" ");
                            }
                        }
                    }
                }
            }
        }
        return msg.toString();
    }

    public void updateGrid(int x, int y, String action) {
        switch (action) {
            case "add" -> addFlag(x - 1, y - 1);
            case "remove" -> removeFlag(x - 1, y - 1);
            case "reveal" -> revealCell(x - 1, y - 1);
            default -> System.err.println("Incorrect entry."); //System.out.println("incorrect entry.");
        }
    }

    public String drawMineMap() {
        StringBuilder msg = new StringBuilder("  ");
        for (int i = 0; i < width; i++) {
            msg.append(i).append(" ");
        }
        msg.append("\n");
        for (int row = 0; row < height; row++) {
            msg.append(row).append(" ");
            for (int col = 0; col < width; col++) {
                if (mainGrid[col][row].getHasMine()) {
                    if (col == width - 1) {
                        msg.append("M\n");
                    } else {
                        msg.append("M ");
                    }
                } else {
                    if (col == width - 1) {
                        msg.append(" \n");
                    } else {
                        msg.append("  ");
                    }
                }
            }
        }
        return msg.toString();
    }

    private void addMines() {
        int minesToPlace = numMines;
        while (minesToPlace > 0) {
            int randomNumX = ThreadLocalRandom.current().nextInt(0, width);
            int randomNumY = ThreadLocalRandom.current().nextInt(0, height);

            if (!mainGrid[randomNumX][randomNumY].getHasMine()) {
                mainGrid[randomNumX][randomNumY].setHasMine(true);
                for (Cell neighbour : getNeighbours(randomNumX, randomNumY)) {
                    neighbour.setNeighbourMineCount(neighbour.getNeighbourMineCount() + 1);
                }
                minesToPlace--;
            }
        }
    }

    private void addFlag(int x, int y) {
        // Look at this again
        if (!mainGrid[x][y].getHasFlag() && !mainGrid[x][y].getIsRevealed()) {
            mainGrid[x][y].setHasFlag(true);
            mainGrid[x][y].setIsRevealed(true);
            System.out.println("Flag added.");
        } else if (mainGrid[x][y].getIsRevealed()) {
            System.out.println("game.minesweeper.Cell already revealed");
        } else {
            System.out.println("There is already a flag.");
        }
    }

    private void removeFlag(int x, int y) {
        // Look at this again
        if (mainGrid[x][y].getHasFlag()) {
            mainGrid[x][y].setHasFlag(false);
            mainGrid[x][y].setIsRevealed(false);
            System.out.println("Flag removed.");
        } else {
            System.out.println("There is no flag to remove.");
        }
    }

    private void revealCell(int x, int y) {
        if (mainGrid[x][y].getHasMine()) {
            hasHitMine = true;
        } else {
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

    private List<Cell> getNeighbours(int x, int y) {
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

    private boolean isInRange(int x, int y) {
        return (x > -1 && x < width) && (y > -1 && y < height);
    }
    // --------------------------------------------------------------------------------
}
