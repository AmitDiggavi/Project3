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
     * testing the getLocation method
     */
    @Test
    public void codeReviewofDataWrangler1() 
    {
    	Graph<ILocation, Double> graph = new Graph<>();
    	
    	//loading the dot file, to use for the data
    	  try {
              new LocationLoader(graph).loadLocations("p3dot.dot");

          } catch (Exception e) {
    	
    	Location location = new Location("Chicago");

        assertEquals("Chicago", location.getLocation());
          }
    }

    /**
     * 
     */
    @Test
    public void codeReviewofDataWranglerTest2()
    {
    	Graph<ILocation, Double> graph = new Graph<>();
    	//loading the dot file, to use for the data
  	  try {
            new LocationLoader(graph).loadLocations("p3dot.dot");

        } catch (Exception e) {
        	
        	assertTrue(graph.containsEdge(new Location("Madison"), new Location("Rockford")));
        }
    }
    
    
}
