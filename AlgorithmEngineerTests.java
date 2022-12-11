import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

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
}
