public class Tesseract extends Mesh4D {

    public Tesseract(float l) {
        float l2 = l / 2;
        localMat = Matrix.translationMatrix(-l2, -l2, -l2, -l2);
//        localMat = Matrix.rotXAMatrix((float)Math.toRadians(30)).multiply(localMat);
//        localMat = Matrix.rotYZMatrix((float)Math.toRadians(10)).multiply(localMat);
//        localMat = Matrix.rotXYMatrix((float)Math.toRadians(10)).multiply(localMat);

        vertices = new Vertex4D[] {
                new Vertex4D(0, 0, 0, 0),
                new Vertex4D(0, l, 0, 0),
                new Vertex4D(l, l, 0, 0),
                new Vertex4D(l, 0, 0, 0),

                new Vertex4D(0, 0, l, 0),
                new Vertex4D(0, l, l, 0),
                new Vertex4D(l, l, l, 0),
                new Vertex4D(l, 0, l, 0),

                new Vertex4D(0, 0, 0, l),
                new Vertex4D(0, l, 0, l),
                new Vertex4D(l, l, 0, l),
                new Vertex4D(l, 0, 0, l),

                new Vertex4D(0, 0, l, l),
                new Vertex4D(0, l, l, l),
                new Vertex4D(l, l, l, l),
                new Vertex4D(l, 0, l, l),
        };

        edges = new Edge4D[] {
                //cube 1 front face
                new Edge4D(vertices[0], vertices[1]),
                new Edge4D(vertices[1], vertices[2]),
                new Edge4D(vertices[2], vertices[3]),
                new Edge4D(vertices[3], vertices[0]),

                //cube 1 back face
                new Edge4D(vertices[4], vertices[5]),
                new Edge4D(vertices[5], vertices[6]),
                new Edge4D(vertices[6], vertices[7]),
                new Edge4D(vertices[7], vertices[4]),

                //cube 1 connecting edges
                new Edge4D(vertices[0], vertices[4]),
                new Edge4D(vertices[1], vertices[5]),
                new Edge4D(vertices[2], vertices[6]),
                new Edge4D(vertices[3], vertices[7]),

                //cube 2 front face
                new Edge4D(vertices[8], vertices[9]),
                new Edge4D(vertices[9], vertices[10]),
                new Edge4D(vertices[10], vertices[11]),
                new Edge4D(vertices[11], vertices[8]),

                //cube 2 back face
                new Edge4D(vertices[12], vertices[13]),
                new Edge4D(vertices[13], vertices[14]),
                new Edge4D(vertices[14], vertices[15]),
                new Edge4D(vertices[15], vertices[12]),

                //cube 2 connecting edges
                new Edge4D(vertices[8], vertices[12]),
                new Edge4D(vertices[9], vertices[13]),
                new Edge4D(vertices[10], vertices[14]),
                new Edge4D(vertices[11], vertices[15]),

                //both cubes' front faces' connecting edges
                new Edge4D(vertices[0], vertices[8]),
                new Edge4D(vertices[1], vertices[9]),
                new Edge4D(vertices[2], vertices[10]),
                new Edge4D(vertices[3], vertices[11]),

                //both cubes' back faces' connecting edges
                new Edge4D(vertices[4], vertices[12]),
                new Edge4D(vertices[5], vertices[13]),
                new Edge4D(vertices[6], vertices[14]),
                new Edge4D(vertices[7], vertices[15]),
        };
    }

    public Vertex4D[] getVertices() {
        //
        return null;
    }
}
