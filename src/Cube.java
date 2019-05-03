import com.jogamp.graph.geom.Vertex;
import com.jogamp.nativewindow.util.Point;

public class Cube implements Mesh{
    private ProjectVertex[] vertices;
    //[i][j]
    //[i] represents the edge
    //[j] represents the vertex numbers connected
    private int[][] edgeData;
    private float z = 10f;

    //I want its origin at (0,0,z)
    public Cube() {
        vertices = new ProjectVertex[] {

                //back face

                //left, bottom, back
                new ProjectVertex(-1, -1, z+1),
                //right, bottom, back
                new ProjectVertex(1, -1, z+1),
                //right, top, back
                new ProjectVertex(1, 1, z+1),
                //left, top, back
                new ProjectVertex(-1, 1, z+1),


                //front face

                //left, bottom, front
                new ProjectVertex(-1, -1, z-1),
                //right, bottom, front
                new ProjectVertex(1, -1, z-1),
                //right, top, front
                new ProjectVertex(1, 1, z-1),
                //left, top, front
                new ProjectVertex(-1, 1, z-1),
        };

        edgeData = new int[][] {
                //bottom back
                {0,1},
                //right back
                {1,2},
        };
    }

    @Override
    public ProjectVertex[] getVertices() {
        return new ProjectVertex[0];
    }

    @Override
    public ProjectEdge[] getEdges() {
        return new ProjectEdge[0];
    }
}
