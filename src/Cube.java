import com.jogamp.graph.geom.Vertex;
import com.jogamp.nativewindow.util.Point;

public class Cube implements Mesh{
    private ProjectVertex vertex1 = new ProjectVertex(-1f, 1f,-1f);
    private ProjectVertex vertex2 = new ProjectVertex(-1f,-1f,-1f);
    private ProjectVertex vertex3 = new ProjectVertex(-1f,-1f,-1f);
    private ProjectVertex vertex4 = new ProjectVertex(-1f,-1f,-1f);

    @Override
    public ProjectVertex[] getVertices() {
        return new ProjectVertex[0];
    }
}
