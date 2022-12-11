

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
                \t1. New York
                \t2. Chicago
                \t3. Los Angeles
                \t4. San Francisco

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
        TextUITester uiTester = new TextUITester("2\nNew York\n7\n");
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
                Current Itinerary: New York -> Unset
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
        TextUITester uiTester = new TextUITester("3\nNew York\n7\n");
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
                Current Itinerary: Unset -> New York
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
        TextUITester uiTester = new TextUITester("2\nSan Francisco\n4\nNew York\n7\n");
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
                Current Itinerary: San Francisco -> Unset
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
                Current Itinerary: San Francisco -> New York -> Unset
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
