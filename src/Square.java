public class Square extends Mesh {
    public Square(float sideLength) {
        float x = sideLength / 2;
        vertices = new Vertex3D[] {
                new Vertex3D(-x, -x, 0),
                new Vertex3D(-x, x, 0),
                new Vertex3D(x, x, 0),
                new Vertex3D(x, -x, 0)
        };

        edges = new Edge3D[] {
                new Edge3D(vertices[0], vertices[1]),
                new Edge3D(vertices[1], vertices[2]),
                new Edge3D(vertices[2], vertices[3]),
                new Edge3D(vertices[3], vertices[0]),
        };
    }
}
