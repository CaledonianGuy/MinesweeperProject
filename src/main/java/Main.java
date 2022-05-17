public class Main {
    public static void main(String[] args) {
        Grid testGrid = new Grid();
        testGrid.buildGrid();
        testGrid.addMines();
        /*
                for (int row = 0; row < testGrid.getHeight(); row++) {
            for (int col = 0; col < testGrid.getWidth(); col++) {
                Cell testCell = testGrid.getGrid()[col][row];
                System.out.println("X: " + testCell.getGridX() + " Y: " + testCell.getGridY());
            }
        }
        */
//        clearScreen();
        System.out.println(testGrid.drawGrid());
//        System.out.println("");
        System.out.println(testGrid.drawMineMap());
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
