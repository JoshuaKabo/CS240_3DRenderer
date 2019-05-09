public class Cube extends Mesh {
    public Cube(float l) {
        float l2 = l / 2;
        localMat = Matrix.translationMatrix(-l2, -l2, -l2);

        vertices = new Vertex3D[] {
            new Vertex3D(0, 0, 0),
            new Vertex3D(0, l, 0),
            new Vertex3D(l, l, 0),
            new Vertex3D(l, 0, 0),

            new Vertex3D(0, 0, l),
            new Vertex3D(0, l, l),
            new Vertex3D(l, l, l),
            new Vertex3D(l, 0, l)
        };

        edges = new Edge3D[] {
            new Edge3D(vertices[0], vertices[1]),
            new Edge3D(vertices[1], vertices[2]),
            new Edge3D(vertices[2], vertices[3]),
            new Edge3D(vertices[3], vertices[0]),

            new Edge3D(vertices[4], vertices[5]),
            new Edge3D(vertices[5], vertices[6]),
            new Edge3D(vertices[6], vertices[7]),
            new Edge3D(vertices[7], vertices[4]),

            new Edge3D(vertices[0], vertices[4]),
            new Edge3D(vertices[1], vertices[5]),
            new Edge3D(vertices[2], vertices[6]),
            new Edge3D(vertices[3], vertices[7]),
        };
    }

    @Override
    public ProjectEdge[] getEdges() {
        return new ProjectEdge[0];
    }
}
