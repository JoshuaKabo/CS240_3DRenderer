import com.jogamp.opengl.*;

public class Canvas2D {
    private GL2 gl;

    public Canvas2D(GL2 gl) {
        this.gl = gl;
    }

    public void clear() {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    }

    public void setColor(float red, float green, float blue, float alpha) {
        gl.glColor4f(red, green, blue, alpha);
    }

    public void strokeWidth(float width) {
        gl.glLineWidth(width);
    }

    public void strokeLine(Vertex2D v1, Vertex2D v2) {
        strokeLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
    }

    public void strokeLine(float x1, float y1, float x2, float y2) {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(x1, y1, 0f);
        gl.glVertex3f(x2, y2, 0f);
        gl.glEnd();
    }

    public void fillPolygon(Vertex2D[] verts) {
        gl.glBegin(GL2.GL_POLYGON);
        for (Vertex2D v : verts) {
            gl.glVertex3f(v.getX(), v.getY(),0f);
        }
        gl.glEnd();
    }
}
