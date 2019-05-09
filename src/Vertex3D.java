public class Vertex3D {
    protected float x;
    protected float y;
    protected float z;

    public Vertex3D() {
        this(0, 0, 0);
    }

    public Vertex3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String toString() {
        return String.format("[%f, %f, %f]", x, y, z);
    }
}
