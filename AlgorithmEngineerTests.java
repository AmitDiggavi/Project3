import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmEngineerTests {
    private Graph<String,Integer> graph;

    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new Graph<>();
        // insert vertices A-F
        graph.insertVertex("Chicago");
        graph.insertVertex("Madison");
        graph.insertVertex("Milwaukee");
        graph.insertVertex("Rochester");
        graph.insertVertex("Springfield");
        graph.insertVertex("Beloit");
        // insert edges
        graph.insertEdge("Chicago","Madison",60);
        graph.insertEdge("Chicago","Milwaukee",20);
        graph.insertEdge("Chicago","Rochester",50);
        graph.insertEdge("Madison","Springfield",10);
        graph.insertEdge("Madison","Milwaukee",20);
        graph.insertEdge("Milwaukee","Madison",30);
        graph.insertEdge("Milwaukee","Beloit",10);
        graph.insertEdge("Rochester","Springfield",30);
        graph.insertEdge("Springfield","Chicago",40);
        graph.insertEdge("Beloit","Chicago",10);
        graph.insertEdge("Beloit","Rochester",10);
    }

    /**
     * Checks the distance/total weight cost from the vertex Chicago to Beloit.
     */
    @Test
    public void testPathCostChicagotoBeloit() {
        assertTrue(graph.getPathCost("Chicago", "Beloit") == 30);
    }

    /**
     * Checks the distance/total weight cost from the vertex Chicago to Beloit.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("Chicago", "Rochester") == 40);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * Chicago to Rochester.
     */
    @Test
    public void testPathChicagotoRochester() {
        assertTrue(graph.shortestPath("Chicago", "Rochester").toString().equals(
            "[Chicago, Milwaukee, Beloit, Rochester]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex Chicago to Chicago.
     */
    @Test
    public void testPathChicagotoChicago() {
        assertEquals("[Chicago]", graph.shortestPath("Chicago", "Chicago").toString());
    }

    /**
     * Checks that if no path from start to end can be found NoSuchElementException is thrown
     */
    @Test
    public void testNoPath() {
        assertThrows(NoSuchElementException.class, () -> {graph.shortestPath("Chicago", "China");});
    }
}
