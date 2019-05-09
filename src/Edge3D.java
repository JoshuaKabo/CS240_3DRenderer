public class Edge3D {
    private Vertex3D v1, v2;

    public Edge3D(Vertex3D v1, Vertex3D v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex3D getVertex1() {
        return v1;
    }

    public Vertex3D getVertex2() {
        return v2;
    }
}
