public class Square extends Mesh4D {
    public Square(float sideLength) {
        float x = sideLength / 2;
        vertices = new Vertex4D[] {
                new Vertex4D(-x, -x, 0, 0),
                new Vertex4D(-x, x, 0, 0),
                new Vertex4D(x, x, 0, 0),
                new Vertex4D(x, -x, 0, 0)
        };

        edges = new Edge4D[] {
                new Edge4D(vertices[0], vertices[1]),
                new Edge4D(vertices[1], vertices[2]),
                new Edge4D(vertices[2], vertices[3]),
                new Edge4D(vertices[3], vertices[0]),
        };
    }
}
