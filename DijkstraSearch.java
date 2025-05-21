import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final Map<V, Double> distTo = new HashMap<>();
    private final Map<V, V> edgeTo = new HashMap<>();
    private final V start;

    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.start = start;
        dijkstra(graph, start);
    }

    private void dijkstra(WeightedGraph<V> graph, V start) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));
        distTo.put(start, 0.0);
        pq.add(graph.getVertex(start));

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();
            if (current == null) continue;
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                V neighbor = entry.getKey().getData();
                double weight = entry.getValue();
                double newDist = distTo.get(current.getData()) + weight;

                if (newDist < distTo.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current.getData());
                    pq.add(entry.getKey());
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(V key) {
        return distTo.containsKey(key);
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
