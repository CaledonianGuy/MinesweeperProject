import game.minesweeper.GameManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameManagerTest extends GameManager {

    @AfterEach
    public void tearDown() throws Exception {
        instance = null;
    }

    /*
    @Test
    public void testMain() {

    }
    */

    @Test
    public void testIsInteger() {
        String testString = null;

        Assertions.assertFalse(GameManager.isInteger(null));

        Assertions.assertFalse(GameManager.isInteger(""));

        Assertions.assertFalse(GameManager.isInteger("-"));

        Assertions.assertTrue(GameManager.isInteger("10"));
    }
}
