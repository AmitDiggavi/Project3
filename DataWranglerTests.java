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
            try {
                new LocationLoader(graph).loadLocations("p3dot.dot");

            } catch (Exception e) {

            }
            assertEquals(graph.getEdgeCount(), 80);

        }
    }

    /**
     * check for specific edges and if they match
     */
    @Test
    public void test5()
    {
        try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {

        }

        assertTrue(graph.containsEdge(new Location("Chicago"), new Location("Milwaukee")));
    }
}
