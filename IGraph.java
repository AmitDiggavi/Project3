import java.util.ArrayList;

public interface IGraph<NodeType,EdgeType extends Number> extends GraphADT<NodeType, EdgeType> {
    // this interface combines with GraphADT and defines no additional methods
    // in addition to the ones it inherits from both these interfaces

    public ArrayList<NodeType> getVertices();
}
