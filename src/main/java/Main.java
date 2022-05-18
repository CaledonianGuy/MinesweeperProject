import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        // Intro - later when adding difficulty

        Grid testGrid = new Grid();
        testGrid.buildGrid();

        Scanner reader = new Scanner(System.in);

        String[] inputs = new String[3];
        String input;

        boolean runningInputCheck;
        boolean running = true;

        do {
            System.out.println(testGrid.drawGrid());

            System.out.println("Which cell would you like to select?");
            System.out.println("Please enter in the form - X, Y.");
            System.out.println("For X this must be between 1 - " + testGrid.getWidth()
                    + " and for Y this must be between 1 - " + testGrid.getHeight());

            runningInputCheck = true;

            do {
                System.out.println("Please enter cell coordinates:");
                String[] tempHolder = reader.nextLine().split(", ");

                for (int i = 0; i < 2; i++) {
                    if (!isInteger(tempHolder[i])) {
                        System.out.println("Invalid input!");
                        break;
                    } else {
                        inputs[i] = tempHolder[i];
                        if (i == 1) {
                            runningInputCheck = false;
                        }
                    }
                }

            } while (runningInputCheck);

            System.out.println("\nWhich operation would you like to perform?");
            System.out.println("You can add flag to cell (add), remove flag from cell (remove), or reveal cell (reveal).");

            runningInputCheck = true;

            do {
                System.out.println("Please enter add, remove, or reveal.");
                input = reader.next().toLowerCase();

                if (!Objects.equals(input, "add")
                        && !Objects.equals(input, "remove")
                        && !Objects.equals(input, "reveal")) {
                    System.out.println("Input invalid!");
                } else {
                    inputs[2] = input;
                    runningInputCheck = false;
                }
            } while (runningInputCheck);

            testGrid.updateGrid(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), inputs[2]);

            if (testGrid.isHasHitMine()) {
                System.out.println("You hit a mine! Better luck next time!");
                running = false;
            }

//            System.out.println("Continue? Y/N");

//            runningInputCheck = true;

//            do {
//                System.out.println("Please enter Y or N");
//                input = reader.next().toUpperCase();
//
//                if (!Objects.equals(input, "Y") && !Objects.equals(input, "N")) {
//                    System.out.println("Input invalid!");
//                } else {
//                    if (Objects.equals(input, "N")) {
//                        running = false;
//                    } else {
//                        clearScreen();
//                    }
//                    runningInputCheck = false;
//                }
//
//            } while (runningInputCheck);

//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            reader.nextLine();
            clearScreen();

        } while (running);

//        if (testGrid.isHasHitMine()) {
//            System.out.println("Better luck next time!");
//        }

        System.out.println("Got to end!!!");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
