import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmEngineerTests {
    private Graph<ILocation,Integer> graph;

    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new Graph<>();
        // insert vertices A-F
        graph.insertVertex(makeLoc("Chicago"));
        graph.insertVertex(makeLoc("Madison"));
        graph.insertVertex(makeLoc("Milwaukee"));
        graph.insertVertex(makeLoc("Rochester"));
        graph.insertVertex(makeLoc("Springfield"));
        graph.insertVertex(makeLoc("Beloit"));
        // insert edges
        graph.insertEdge(makeLoc("Chicago"), makeLoc("Madison"),60);
        graph.insertEdge(makeLoc("Chicago"), makeLoc("Milwaukee"),20);
        graph.insertEdge(makeLoc("Chicago"), makeLoc("Rochester"),50);
        graph.insertEdge(makeLoc("Madison"), makeLoc("Springfield"),10);
        graph.insertEdge(makeLoc("Madison"), makeLoc("Milwaukee"),20);
        graph.insertEdge(makeLoc("Milwaukee"), makeLoc("Madison"),30);
        graph.insertEdge(makeLoc("Milwaukee"), makeLoc("Beloit"),10);
        graph.insertEdge(makeLoc("Rochester"), makeLoc("Springfield"),30);
        graph.insertEdge(makeLoc("Springfield"), makeLoc("Chicago"),40);
        graph.insertEdge(makeLoc("Beloit"), makeLoc("Chicago"),10);
        graph.insertEdge(makeLoc("Beloit"), makeLoc("Rochester"),10);
    }

    /**
     * Checks the distance/total weight cost from the vertex Chicago to Beloit.
     */
    @Test
    public void testPathCostChicagotoBeloit() {
        assertTrue(graph.getPathCost(makeLoc("Chicago"), makeLoc("Beloit")) == 30);
    }

    /**
     * Checks the distance/total weight cost from the vertex Chicago to Beloit.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost(makeLoc("Chicago"), makeLoc("Rochester")) == 40);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * Chicago to Rochester.
     */
    @Test
    public void testPathChicagotoRochester() {
        assertTrue(graph.shortestPath(makeLoc("Chicago"), makeLoc("Rochester")).toString().equals(
            "[Chicago, Milwaukee, Beloit, Rochester]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex Chicago to Chicago.
     */
    @Test
    public void testPathChicagotoChicago() {
        assertEquals("[Chicago]", graph.shortestPath(makeLoc("Chicago"), makeLoc("Chicago")).toString());
    }

    /**
     * Checks that if no path from start to end can be found NoSuchElementException is thrown
     */
    @Test
    public void testNoPath() {
        assertThrows(NoSuchElementException.class, () -> {graph.shortestPath(makeLoc("Chicago"), makeLoc("China"));});
    }

    public ILocation makeLoc(String name) {
        return new Location(name);
    }

    /**
     * Uses the backend method calculateRoute which uses the shortestPath method
     * to find the route given two locations in a graph
     */
    @Test
    public void integrationTestCalculateRoute() {
        Backend backend = new Backend();
        ArrayList<ILocation> path = new ArrayList<>();
        String expectedRoute = "Madison,Middleton,La Crosse,Rochester";
        backend.setCurrentLocation("Madison");
        backend.setTargetLocation("Rochester");
        path = backend.calculateRoute();
        String calculatedRoute = "";

        for(ILocation p: path)
        {
            calculatedRoute = calculatedRoute + (calculatedRoute.length() > 0? ",": "") +
                p.getLocation();
        }

        assertEquals(expectedRoute, calculatedRoute);
    }

    /**
     * Uses the backend method calculateRouteDistance which uses the shortestPathand getPathCost
     * methods to find the route distance given two locations in a graph
     */
    @Test
    public void integrationTestCalculateRouteDistance() {
        Backend backend = new Backend();
        backend.setCurrentLocation("Madison");
        backend.setTargetLocation("Rochester");
        ArrayList<String> stops = new ArrayList<>();
        ArrayList<Double> distances = backend.calculateRouteDistance();
        Double totalDistance = 0.0;

        for (Double d : distances) {
            totalDistance += d;
        }

        assertEquals(totalDistance, 215.0);
    }

    /**
     * Tests the find route functionality.
     */
    @Test
    public void codeReviewofFrontendDeveloperTest1() {
        TextUITester uiTester = new TextUITester("2\nMadison\n3\nChicago\n5\n7\n");
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
                Current Itinerary: Madison -> Chicago
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit
                
                The shortest route is:
                Route: Madison -> Rockford (75.0 Miles) -> Chicago (90.0 Miles)
                Total Distance: 165.0 Miles
                                
                Safe travels!
                                
                You are in the Main Menu:
                Current Itinerary: Madison -> Chicago
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
     * Tests the reset route functionality.
     */
    @Test
    public void codeReviewofFrontendDeveloperTest2() {
        TextUITester uiTester = new TextUITester("2\nMadison\n3\nChicago\n6\n7\n");
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
                Current Itinerary: Madison -> Chicago
                Choose an option:
                \t 1. List all cities
                \t 2. Set current city
                \t 3. Set target city
                \t 4. Add stop
                \t 5. Find route
                \t 6. Reset stops
                \t 7. Exit
                
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
}
