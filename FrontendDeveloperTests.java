import org.junit.jupiter.api.Test;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the Frontend class.
 */
public class FrontendDeveloperTests {

    /**
     * Tests the main menu method.
     */
    @Test
    public void testMainMenu() {
        TextUITester uiTester = new TextUITester("7\n");
        Scanner scn = new Scanner(System.in);
        IBackend backend = new BackendPlaceholder();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = "Welcome to MadMapper\n" +
                "—--------------------------------------------------\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n\n";

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
        IBackend backend = new BackendPlaceholder();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = "Welcome to MadMapper\n" +
                "—--------------------------------------------------\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n" +
                "\n" +
                "List of cities:\n" +
                "\t1. New York\n" +
                "\t2. Chicago\n" +
                "\t3. Los Angeles\n" +
                "\t4. San Francisco\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n\n";

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
        IBackend backend = new BackendPlaceholder();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = "Welcome to MadMapper\n" +
                "—--------------------------------------------------\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n" +
                "\n" +
                "Enter City:\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: New York -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n\n";

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
        IBackend backend = new BackendPlaceholder();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = "Welcome to MadMapper\n" +
                "—--------------------------------------------------\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n" +
                "\n" +
                "Enter City:\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> New York\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n\n";

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
        IBackend backend = new BackendPlaceholder();
        IFrontend frontend = new Frontend(backend, scn);
        frontend.runCommandLoop();

        String expected = "Welcome to MadMapper\n" +
                "—--------------------------------------------------\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: Unset -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n" +
                "\n" +
                "Enter City:\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: San Francisco -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n" +
                "\n" +
                "Enter City:\n" +
                "\n" +
                "You are in the Main Menu:\n" +
                "Current Itinerary: San Francisco -> New York -> Unset\n" +
                "Choose an option:\n" +
                "\t 1. List all cities\n" +
                "\t 2. Set current city\n" +
                "\t 3. Set target city\n" +
                "\t 4. Add stop\n" +
                "\t 5. Find route\n" +
                "\t 6. Reset stops\n" +
                "\t 7. Exit\n\n";

        // Check that the output is correct
        assertEquals(expected, uiTester.checkOutput());
    }
}
