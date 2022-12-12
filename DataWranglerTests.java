import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DataWranglerTests {
    Graph<ILocation, Double> graph;

    @BeforeEach
    public void createGraph() {
        this.graph = new Graph<>();
    }


    /**
     * testing the getLocation method
     */
    @Test
    public void test1() {
        Location location = new Location("Madison");

        assertEquals("Madison", location.getLocation());
    }

    /**
     * testing if the method loads the correct amount of locations
     */
    @Test
    public void test2() {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        assertEquals(21, graph.getVertexCount());
    }

    /**
     * tests if the method throws an expection if file is not found
     */
    @Test
    public void test3() {
        boolean bool = false;
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3.dot");
        } catch (Exception exception) {
            bool = true;
        }
        assertTrue(bool);
    }

    /**
     * test if the amount of edges is correct
     */
    @Test
    public void test4() {
        {
            // loading the graph with a try catch, so it can throw the FileNotFoundException.
            try {
                new LocationLoader(graph).loadLocations("p3dot.dot");

            } catch (Exception e) {

            }
            assertEquals(graph.getEdgeCount(), 75);

        }
    }

    /**
     * check for specific edges and if they match
     */
    @Test
    public void test5()
    {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        assertTrue(graph.containsEdge(new Location("Chicago"), new Location("Milwaukee")));
    }

    /**
     * Tests if the removeEdge method works as needed.
     */
    @Test
    public void IntegrationTest1()
    {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        graph.removeEdge(new Location("Chicago"), new Location("Milwaukee"));

        assertEquals(graph.getEdgeCount(), 74);
    }

    /**
     * This method tests the containsEdge method.
     */
    @Test
    public void IntegrationTest2()
    {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        assertTrue(graph.containsEdge(new Location("Madison"), new Location("Janesville")));
        assertFalse(graph.containsEdge(new Location("Madison"), new Location("Springfield")));
    }

    /**
     * Checking the getWeight method to see if it will return the correct number.
     */
    @Test
    public void CodeReviewOfAlgorithmEngineer1()
    {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }
       assertEquals(graph.getWeight(new Location("Chicago"), new Location("Milwaukee")), 90);
    }

    @Test
    public void CodeReviewOfAlgorithmEngineer2()
    {
        // loading the graph with a try catch, so it can throw the FileNotFoundException.
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        graph.insertEdge( new Location("Madison"), new Location("Springfield"), 100.0);

        assertEquals(76, graph.getEdgeCount());

    }

}
