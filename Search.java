import java.util.List;

public interface Search<V> {
    boolean hasPathTo(V key);
    List<V> pathTo(V key);
}
