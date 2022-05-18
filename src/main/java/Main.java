public class Main {
    public static void main(String[] args) {
        Grid testGrid = new Grid();
        testGrid.buildGrid();
//        clearScreen();
        System.out.println(testGrid.drawGrid());
        System.out.println(testGrid.drawMineMap());
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
