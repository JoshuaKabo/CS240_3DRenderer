import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.awt.TextRenderer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class RenderFrame extends JFrame implements GLEventListener, KeyListener, MouseMotionListener, MouseListener {
    private int width;
    private int height;
    private float aspectRatio;
    private float angle = 0;
    private int framecount = 0;

    private float mX = 0, mY = 0;

    private JButton jb = new JButton("test");




    private TextRenderer textRenderer;

    private Scene scene;



    public RenderFrame(int width, int height) {

        super("3D Renderer");

        jb.setBounds(0,0,100,100);

        //this.add(jb);

        textRenderer = new TextRenderer(new Font("Dialog", Font.BOLD, 24));



        this.width = width;
        this.height = height;
        aspectRatio = (float) width / height;
        scene = null;

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas glCanvas = new GLCanvas(capabilities);
        glCanvas.addGLEventListener(this);
        glCanvas.setFocusable(true);
        glCanvas.addKeyListener(this);
        glCanvas.addMouseListener(this);
        glCanvas.addMouseMotionListener(this);


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
        Timer timer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Matrix rotMat = Matrix.rotZMatrix((float) Math.sin(Math.toRadians(framecount) + 240) * 0.015f);
                rotMat = rotMat.multiply(Matrix.rotXMatrix((float) Math.sin(Math.toRadians(framecount)) * 0.015f));
                rotMat = rotMat.multiply(Matrix.rotYMatrix(0.01f));

                if (scene != null) {
                    List<WorldObject> objects = scene.getObjects();
                    for (WorldObject object : objects) {
                        object.applyScaleRotation(rotMat);
                    }
                }
                drawable.display();
                framecount++;
            }
        });
        timer.start();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        Canvas2D canvas = new Canvas2D(gl);
        canvas.clear();



        textRenderer.beginRendering(600, 600);
        textRenderer.draw(" " + mX + " " + mY, 20, 20);
        textRenderer.endRendering();

        canvas.setColor(1f, 1f, 1f, 0.5f);
        canvas.strokeWidth(2);

//        rot = rot.multiply(Matrix.rotYMatrix((float)Math.toRadians(angle + 60)));
//        Matrix rot = Matrix.rotZMatrix((float)Math.toRadians(angle + 45));

        float scaleDim;
        Matrix screenScale;
        if (width > height) {
            scaleDim = (float) height / width;
            screenScale = Matrix.scaleMatrix(scaleDim,1, 1);
        } else {
            scaleDim = (float) width / height;
            screenScale = Matrix.scaleMatrix(1, scaleDim, 1);
        }

        if (scene != null) {
//            List<WorldObject> objects = scene.getObjects();
            WorldObject object = scene.peek();
            if (object != null) {
//            for (WorldObject object: objects) {
                Mesh mesh = object.getMesh();
                Matrix modelMat = object.getModelMat();

                modelMat = screenScale.multiply(modelMat);

                for (Edge3D edge : mesh.getEdges()) {
                    Vertex3D v1 = edge.getVertex1();
                    Vertex3D v2 = edge.getVertex2();

                    v1 = modelMat.multiplyVert(v1);
                    v2 = modelMat.multiplyVert(v2);

                    float z1 = v1.getZ();
                    float x1 = v1.getX() / -z1;
                    float y1 = v1.getY() / -z1;

                    float z2 = v2.getZ();
                    float x2 = v2.getX() / -z2;
                    float y2 = v2.getY() / -z2;

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
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        char pressed = e.getKeyChar();
//        System.out.println(pressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        scene.enqueue(scene.dequeue());
//        char pressed = e.getKeyChar();
//        System.out.println(pressed);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("enter");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        //System.out.println(e);
    }
}
