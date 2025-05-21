import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final Set<V> visited = new HashSet<>();
    private final Map<V, V> edgeTo = new HashMap<>();
    private final V start;

    public DepthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        dfs(graph, start);
    }

    private void dfs(UnweightedGraph<V> graph, V current) {
        visited.add(current);
        Vertex<V> vertex = graph.getVertex(current);
        if (vertex != null) {
            for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                if (!visited.contains(neighborData)) {
                    edgeTo.put(neighborData, current);
                    dfs(graph, neighborData);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V key) {
        return visited.contains(key);
    }

    @Override
    public List<V> pathTo(V key) {
        if (!hasPathTo(key)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V x = key; x != start; x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
