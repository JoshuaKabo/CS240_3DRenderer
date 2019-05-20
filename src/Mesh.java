public class Mesh {
    protected Vertex3D vertices[];
    protected Edge3D edges[];

    protected Matrix localMat; // can be null

    /*
    all mesh objects will have to reveal their vertices. In the future, the camera will ask for all vertices in the
    scene and draw rays to them
     */
    public Vertex3D[] getVertices() {
        return vertices;
    }

    public Edge3D[] getEdges() {
        return edges;
    }

    public Matrix getLocalMat() {
        return localMat;
    }
}
