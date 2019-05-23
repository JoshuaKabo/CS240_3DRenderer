public class Vertex4D {
    protected float x;
    protected float y;
    protected float z;
    protected float a;

    public Vertex4D() {
        this(0, 0, 0, 0);
    }

    public Vertex4D(float x, float y, float z, float a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
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

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public String toString() {
        return String.format("[%f, %f, %f, %f]", x, y, z, a);
    }

    @Override
    public boolean equals(Object o) {
        Vertex4D v = (Vertex4D)o;
        if(v.x == x && v.y == y && v.z == z && v.a == a) {
            return true;
        }
        else return false;
    }
}
