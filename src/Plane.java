public class Plane{
//    private ProjectVertex[] vertices;
//    private ProjectEdge[] edges;
//    private float width, height;
//
//    //creates a plane perpendicular to the z-axis, with lower left corner on origin, with appropriate length and width
//    public Plane() {
//        this.width = 1;
//        this.height = 1;
//        vertices = new ProjectVertex[] {
//                //Bottom Left
//                new ProjectVertex(0,0,0),
//                //Bottom Right
//                new ProjectVertex(1,0,0),
//                //Top Left
//                new ProjectVertex(0, 1, 0),
//                //Top Right
//                new ProjectVertex(1, 1, 0)
//        };
//
//        edges = new ProjectEdge[] {
//                //Left
//                new ProjectEdge(vertices[0], vertices[2]),
//                //Right
//                new ProjectEdge(vertices[1], vertices[3]),
//                //Top
//                new ProjectEdge(vertices[2], vertices[3]),
//                //Bottom
//                new ProjectEdge(vertices[0], vertices[1])
//        };
//    }
//
//    public Plane(float width, float height) {
//        this.width = width;
//        this.height = height;
//        vertices = new ProjectVertex[] {
//                //Bottom Left
//                new ProjectVertex(0,0,0),
//                //Bottom Right
//                new ProjectVertex(1*width,0,0),
//                //Top Left
//                new ProjectVertex(0, 1*height, 0),
//                //Top Right
//                new ProjectVertex(1*width, 1*height, 0)
//        };
//
//        edges = new ProjectEdge[] {
//                //Left
//                new ProjectEdge(vertices[0], vertices[2]),
//                //Right
//                new ProjectEdge(vertices[1], vertices[3]),
//                //Top
//                new ProjectEdge(vertices[2], vertices[3]),
//                //Bottom
//                new ProjectEdge(vertices[0], vertices[1])
//        };
//    }
//
//    public void makeUpright()
//    {
//
//    }
//
//    //z as forward, move forward
//    public void translateZ(float amount){
//        for(ProjectVertex vertex : vertices) {
//            vertex.setZ(vertex.getZ() + amount);
//        }
//    }
//
//    @Override
//    public ProjectVertex[] getVertices() {
//        return vertices;
//    }
//
//    @Override
//    public int[][] getEdges() {
//        return new int [0][0];
//    }
}
