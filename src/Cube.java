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

                //back face

                //bottom back
                {0,1},
                //left back
                {0,3},
                //right back
                {1,2},
                //top back
                {2,3},

                //front face

                //bottom front
                {4,5},
                //left front
                {4,7},
                //right front
                {5,6},
                //top front
                {6,7},

                //connecting pieces/side faces

                //left bot side
                {0,4},
                //right bot side
                {1,5},
                //left top side
                {3,7},
                //right top side
                {2,6}

        };
    }

    @Override
    public ProjectVertex[] getVertices() {
        return vertices;
    }

    @Override
    public int[][] getEdges() {
        return edgeData;
    }
}
