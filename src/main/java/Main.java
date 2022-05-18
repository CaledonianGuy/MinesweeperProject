import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Intro - later when adding difficulty

        Grid testGrid = new Grid();
        testGrid.buildGrid();
        System.out.println(testGrid.drawGrid());
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
