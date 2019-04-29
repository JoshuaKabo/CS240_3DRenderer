public interface Mesh {
    /*
    all mesh objects will have to reveal their vertices. In the future, the camera will ask for all vertices in the
    scene and draw rays to them
     */
    public ProjectVertex[] getVertices();

    public ProjectEdge[] getEdges();

}
