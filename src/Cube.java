public class Cube extends Mesh4D {
    public Cube(float l) {
        float l2 = l / 2;
        localMat = Matrix.translationMatrix(-l2, -l2, -l2, 0);

        vertices = new Vertex4D[] {
            new Vertex4D(0, 0, 0, 0),
            new Vertex4D(0, l, 0, 0),
            new Vertex4D(l, l, 0, 0),
            new Vertex4D(l, 0, 0, 0),

            new Vertex4D(0, 0, l, 0),
            new Vertex4D(0, l, l, 0),
            new Vertex4D(l, l, l, 0),
            new Vertex4D(l, 0, l, 0)
        };

        edges = new Edge4D[] {
            new Edge4D(vertices[0], vertices[1]),
            new Edge4D(vertices[1], vertices[2]),
            new Edge4D(vertices[2], vertices[3]),
            new Edge4D(vertices[3], vertices[0]),

            new Edge4D(vertices[4], vertices[5]),
            new Edge4D(vertices[5], vertices[6]),
            new Edge4D(vertices[6], vertices[7]),
            new Edge4D(vertices[7], vertices[4]),

            new Edge4D(vertices[0], vertices[4]),
            new Edge4D(vertices[1], vertices[5]),
            new Edge4D(vertices[2], vertices[6]),
            new Edge4D(vertices[3], vertices[7]),
        };
    }
}
