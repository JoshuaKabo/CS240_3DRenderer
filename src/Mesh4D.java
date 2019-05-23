import java.util.*;

public class Mesh4D {
    protected Vertex4D[] vertices;
    protected Edge4D[] edges;

    protected Matrix localMat; // can be null

    public Iterable<Edge4D> getEdges() {
        return Arrays.asList(edges);
    }

    public Matrix getLocalMat() {
        return localMat;
    }
}
