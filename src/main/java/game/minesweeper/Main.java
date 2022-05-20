package game.minesweeper;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Intro - later when adding difficulty

        Grid mainGrid = new Grid();
        mainGrid.buildGrid();

        Scanner reader = new Scanner(System.in);

        String[] inputs = new String[3];
        String input;

        boolean runningInputCheck;
        boolean running = true;

        while (running) {
            System.out.println(mainGrid.drawGrid());

            System.out.println("Which cell would you like to select?");
            System.out.println("Please enter in the form - X, Y. Where X is the column and Y is the row.");
            System.out.println("For X this must be between 1 - " + mainGrid.getWidth()
                    + " and for Y this must be between 1 - " + mainGrid.getHeight() + ".");

            runningInputCheck = true;

            while (runningInputCheck) {
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

            System.out.println("\nWhich operation would you like to perform?");
            System.out.println("You can add flag to cell (add)," +
                    "remove flag from cell (remove)," +
                    "or reveal cell (reveal).");

            runningInputCheck = true;

            while (runningInputCheck) {
                System.out.println("Please enter add, remove, or reveal.");
                input = reader.next().toLowerCase();

                if (!Objects.equals(input, "add")
                        && !Objects.equals(input, "remove")
                        && !Objects.equals(input, "reveal")) {
                    System.err.println("Input invalid!");
                } else {
                    inputs[2] = input;
                    runningInputCheck = false;
                }
            }

            mainGrid.updateGrid(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), inputs[2]);
            System.out.println(mainGrid.getMsgOutput());

            if (mainGrid.getIsHasHitMine()) {
                System.out.println("\nYou hit a mine! Better luck next time!");
                running = false;
            }

            System.out.println();
            reader.nextLine();
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
}
