import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final Map<V, V> edgeTo = new HashMap<>();
    private final Set<V> visited = new HashSet<>();
    private final V start;

    public BreadthFirstSearch(UnweightedGraph<V> graph, V start) {
        this.start = start;
        bfs(graph, start);
    }

    private void bfs(UnweightedGraph<V> graph, V root) {
        Queue<V> queue = new LinkedList<>();
        visited.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            V current = queue.poll();
            Vertex<V> vertex = graph.getVertex(current);
            if (vertex != null) {
                for (Vertex<V> neighbor : vertex.getAdjacentVertices().keySet()) {
                    V neighborData = neighbor.getData();
                    if (!visited.contains(neighborData)) {
                        visited.add(neighborData);
                        edgeTo.put(neighborData, current);
                        queue.add(neighborData);
                    }
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
