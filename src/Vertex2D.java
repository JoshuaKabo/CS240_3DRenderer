public class Vertex2D extends Vertex3D {
    public Vertex2D(float x, float y) {
        super(x, y, 0);
    }

    public float getZ() {
        throw new IllegalArgumentException("z coord not supported in 2d");
    }

    public void setZ(float f) {
        throw new IllegalArgumentException("z coord not supported in 2d");
    }

    public String toString() {
        return String.format("[%f, %f]", x, y);
    }
}
