package game.minesweeper;

import java.util.Objects;
import java.util.Scanner;

public class Main {

//    protected Main() {}

    private static final String[] inputs = new String[3];
    private static boolean runningInputCheck;

    public static void main(String[] args) {

        // Intro - later when adding difficulty

        Grid mainGrid = new Grid();
        mainGrid.buildGrid();

        boolean running = true;

        while (running) {
            System.out.println(mainGrid.drawGrid());

            System.out.println("Which cell would you like to select?");
            System.out.println("Please enter in the form - X, Y. Where X is the column and Y is the row.");
            System.out.println("For X this must be between 1 - " + mainGrid.getWidth()
                    + " and for Y this must be between 1 - " + mainGrid.getHeight() + ".");

            runningInputCheck = true;

            while (runningInputCheck) {
                coordChecker();
            }

            System.out.println("\nWhich operation would you like to perform?");
            System.out.println("You can add flag to cell (add)," +
                    "remove flag from cell (remove)," +
                    "or reveal cell (reveal).");

            runningInputCheck = true;

            while (runningInputCheck) {
                actionChecker();
            }

            mainGrid.updateGrid(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), inputs[2]);

            String msg = mainGrid.getMsgOutput();
            if (msg.equals("Incorrect entry.") || msg.equals("Coordinates not in range.")) {
                System.err.println(msg);
            } else {
                System.out.println(msg);
            }

            if (mainGrid.getHasHitMine()) {
                System.out.println("\nYou hit a mine! Better luck next time!\n");
                running = false;
            }

            if (mainGrid.getCellsToReveal() <= 0) {
                System.out.println("\nYou won!\n");
                running = false;
            }
        }

        mainGrid.revealAll();
        System.out.println(mainGrid.drawGrid());
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }

        int length = str.length();

        if (length == 0) {
            return false;
        }

        int i = 0;

        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }

            i = 1;
        }

        for (; i < length; i++) {
            char c = str.charAt(i);

            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    public static void coordChecker() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter cell coordinates:");
        String[] tempHolder = reader.nextLine().split(", ");

        for (int i = 0; i < 2; i++) {
            if (!isInteger(tempHolder[i])) {
                System.err.println("Input invalid!");
                break;
            } else {
                inputs[i] = tempHolder[i];
                if (i == 1) {
                    runningInputCheck = false;
                }
            }
        }
    }

    public static void actionChecker() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Please enter add, remove, or reveal.");
        String input = reader.next().toLowerCase();

        if (!Objects.equals(input, "add")
                && !Objects.equals(input, "remove")
                && !Objects.equals(input, "reveal")) {
            System.err.println("Input invalid!");
        } else {
            inputs[2] = input;
            runningInputCheck = false;
        }
    }

//    protected void run(String string) throws Exception { }
//
//    protected void logException() { }
//
//    protected static Main instance = null;
//
//    private static Main newGameManager() {
//        return instance == null ? new Main() : instance;
//    }
}
