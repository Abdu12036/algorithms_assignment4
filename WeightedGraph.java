import java.util.*;

public class WeightedGraph<V> {
    private final boolean isDirected;
    private final Map<V, Vertex<V>> vertices = new HashMap<>();

    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(V source, V dest, double weight) {
        Vertex<V> sourceVertex = vertices.computeIfAbsent(source, Vertex::new);
        Vertex<V> destVertex = vertices.computeIfAbsent(dest, Vertex::new);
        sourceVertex.addAdjacentVertex(destVertex, weight);
        if (!isDirected) {
            destVertex.addAdjacentVertex(sourceVertex, weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}
