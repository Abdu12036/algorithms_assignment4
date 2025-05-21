public class UnweightedGraph<V> extends WeightedGraph<V> {
    public UnweightedGraph(boolean isDirected) {
        super(isDirected);
    }

    public void addEdge(V source, V dest) {
        super.addEdge(source, dest, 1.0);
    }
}
