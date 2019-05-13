import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.math.Matrix4;
//import com.sun.javafx.geom.Edge;

import javax.swing.JFrame;
import java.util.List;

public class RenderFrame extends JFrame implements GLEventListener {
    private int width;
    private int height;
    private float aspectRatio;
    private float angle = 0;

    private Scene scene;

    public RenderFrame(int width, int height) {
        super("3D Renderer");

        this.width = width;
        this.height = height;
        aspectRatio = (float) width / height;
        scene = null;

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas glCanvas = new GLCanvas(capabilities);
        glCanvas.addGLEventListener(this);

        this.setSize(this.width, this.height);
        this.getContentPane().add(glCanvas);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);

        glCanvas.requestFocusInWindow();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
//        GL2 gl = drawable.getGL().getGL2();
//        gl.glClearColor(0.0f, 0.0f, 1f, 0.5f);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        Canvas2D canvas = new Canvas2D(gl);
        canvas.clear();

        canvas.setColor(1f, 1f, 1f, 0.5f);
        canvas.strokeWidth(2);

//        Matrix rot = Matrix.rotXMatrix((float)Math.toRadians(angle));
//        rot = rot.multiply(Matrix.rotYMatrix((float)Math.toRadians(angle + 60)));
        Matrix rot = Matrix.rotZMatrix((float)Math.toRadians(angle + 45));

        if (scene != null) {
            List<WorldObject> objects = scene.getObjects();
            for (WorldObject object: objects) {
                Mesh mesh = object.getMesh();
                Matrix modelMat = object.getModelMatrix();
                System.out.println(modelMat);

                for (Edge3D edge : mesh.getEdges()) {
                    Vertex3D v1 = edge.getVertex1();
                    Vertex3D v2 = edge.getVertex2();

                    v1 = modelMat.multiplyVert(v1);
                    v2 = modelMat.multiplyVert(v2);

                    v1 = rot.multiplyVert(v1);
                    v2 = rot.multiplyVert(v2);

//                    v1 = trans2.multiplyVert(v1);
//                    v2 = trans2.multiplyVert(v2);

                    float z1 = v1.getZ();
                    float x1 = v1.getX() / z1;
                    float y1 = v1.getY() / z1;

                    float z2 = v2.getZ();
                    float x2 = v2.getX() / z2;
                    float y2 = v2.getY() / z2;

                    System.out.println(String.format("[%f.2, %f.2, %f.2]", x1, y1, z1));
                    System.out.println(String.format("[%f.2, %f.2, %f.2]", x2, y2, z2));
                    System.out.println();

                    canvas.strokeLine(x1, y1, x2, y2);
                }
            }
        }
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;

        aspectRatio = (float) this.width / this.height;

        angle += 2.5;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
