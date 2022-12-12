import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the src.Frontend class.
 */
public class FrontendDeveloperTests {

    /**
     * Tests the main menu method.
     */
    @Test
    public void testMainMenu() {
        TextUITester uiTester = new TextUITester("7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new Backend();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = """
                Welcome to MadMapper
                —--------------------------------------------------

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                """;

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }

    /**
     * Tests the list cities method.
     */
    @Test
    public void testListCities() {
        TextUITester uiTester = new TextUITester("1\n7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new Backend();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = """
                Welcome to MadMapper
                —--------------------------------------------------

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                List of cities:
                \t1. Appleton
                \t2. Chicago
                \t3. Eau Claire
                \t4. Green Bay
                \t5. Janesville
                \t6. Kenosha
                \t7. La Crosse
                \t8. Madison
                \t9. Middleton
                \t10. Milwaukee
                \t11. Minneapolis
                \t12. Naperville
                \t13. Oshkosh
                \t14. Rochester
                \t15. Rockford
                \t16. Saint Paul
                \t17. Springfield
                \t18. Stevens Point
                \t19. Waukesha
                \t20. Whitewater
                \t21. Wisconsin Dells

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                """;

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }

    /**
     * Tests the set current city method.
     */
    @Test
    public void testSetCurrentCity() {
        TextUITester uiTester = new TextUITester("2\nMadison\n7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new Backend();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = """
                Welcome to MadMapper
                —--------------------------------------------------

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                Enter City:

                You are in the Main Menu:
                Current Itinerary: Madison -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                """;

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }

    /**
     * Tests the set target city method.
     */
    @Test
    public void testSetTargetCity() {
        TextUITester uiTester = new TextUITester("3\nMadison\n7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new Backend();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = """
                Welcome to MadMapper
                —--------------------------------------------------

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                Enter City:

                You are in the Main Menu:
                Current Itinerary: Unset -> Madison
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                """;

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }

    /**
     * Tests the add stop method.
     */
    @Test
    public void testAddStop() {
        TextUITester uiTester = new TextUITester("2\nMadison\n4\nChicago\n7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new Backend();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = """
                Welcome to MadMapper
                —--------------------------------------------------

                You are in the Main Menu:
                Current Itinerary: Unset -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                Enter City:

                You are in the Main Menu:
                Current Itinerary: Madison -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                Enter City:

                You are in the Main Menu:
                Current Itinerary: Madison -> Chicago -> Unset
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit

                """;

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }
}
