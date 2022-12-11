import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphCreator<NodeType, EdgeType> implements IGraph<NodeType, Double> {
    // this is a placeholder class, so I can do my tests and load the files properly

    ArrayList<String[]> edges = new ArrayList<>();
    ArrayList<ILocation> vertices = new ArrayList<>();

    public boolean insertVertex(NodeType data) {

        return vertices.add((ILocation) data);
    }

    @Override
    public boolean removeVertex(NodeType data) {
        return false;
    }

    @Override
    public boolean insertEdge(NodeType source, NodeType target, Double weight) {
        String weightString = weight.toString();
        return edges.add(new String[]{source.toString(), target.toString(), weightString});
    }
    @Override
    public boolean removeEdge(NodeType source, NodeType target) {
        return false;
    }

    @Override
    public boolean containsVertex(NodeType data) {
        for (ILocation location : vertices) {
            if (location.getLocation().equals(data)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsEdge(NodeType source, NodeType target) {
        for (String[] edge : edges) {
            System.out.println(Arrays.deepToString(edge));
            if (new Location(edge[0]).equalsTo((ILocation) source) && new Location(edge[1]).equalsTo((ILocation) target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Double getWeight(NodeType source, NodeType target) {
        for (String[] edge : edges) {
            if (edge[0].equals(source) && edge[1].equals(target)) {
                return Double.parseDouble(edge[2]);
            }
        }
        return null;
    }

    @Override
    public List<NodeType> shortestPath(NodeType start, NodeType end) {
        return null;
    }

    @Override
    public double getPathCost(NodeType start, NodeType end) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return edges.isEmpty() || vertices.isEmpty();
    }

    @Override
    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }
}
