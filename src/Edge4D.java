public class Edge4D {
    private Vertex4D v1, v2;

    public Edge4D(Vertex4D v1, Vertex4D v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex4D getVertex1() {
        return v1;
    }

    public Vertex4D getVertex2() {
        return v2;
    }

    public String toString() {
        return String.format("[\n\t%s,\n\t%s\n]", v1.toString(), v2.toString());
    }

}
