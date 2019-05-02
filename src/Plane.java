public class Plane implements Mesh{
    private ProjectVertex[] Vertices;
    private ProjectEdge[] Edges;

    //creates a plane perpendicular to the z-axis, with lower left corner on origin, with appropriate length and width
    public Plane() {
        Vertices = new ProjectVertex[] {
                //Bottom Left
                new ProjectVertex(0,0,0),
                //Bottom Right
                new ProjectVertex(1,0,0),
                //Top Left
                new ProjectVertex(0, 1, 0),
                //Top Right
                new ProjectVertex(1, 1, 0)
        };

        Edges = new ProjectEdge[] {
                //Left
                new ProjectEdge(Vertices[0],Vertices[2]),
                //Right
                new ProjectEdge(Vertices[1],Vertices[3]),
                //Top
                new ProjectEdge(Vertices[2],Vertices[3]),
                //Bottom
                new ProjectEdge(Vertices[0],Vertices[1])
        };
    }

    public Plane(float width, float height) {
        Vertices = new ProjectVertex[] {
                //Bottom Left
                new ProjectVertex(0,0,0),
                //Bottom Right
                new ProjectVertex(1*width,0,0),
                //Top Left
                new ProjectVertex(0, 1*height, 0),
                //Top Right
                new ProjectVertex(1*width, 1*height, 0)
        };

        Edges = new ProjectEdge[] {
                //Left
                new ProjectEdge(Vertices[0],Vertices[2]),
                //Right
                new ProjectEdge(Vertices[1],Vertices[3]),
                //Top
                new ProjectEdge(Vertices[2],Vertices[3]),
                //Bottom
                new ProjectEdge(Vertices[0],Vertices[1])
        };
    }

    public void Rotate(float degrees)
    {

    }

    @Override
    public ProjectVertex[] getVertices() {
        return Vertices;
    }

    @Override
    public ProjectEdge[] getEdges() {
        return Edges;
    }
}
