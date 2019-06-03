import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.awt.TextRenderer;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RenderFrame extends JFrame implements GLEventListener, KeyListener, MouseMotionListener, MouseListener {
    private int width;
    private int height;

    private int[] lastMousePos;

    private float mX = 0, mY = 0;

    private TextRenderer textRenderer;

    private Scene scene;



    public RenderFrame(int width, int height) {

        super("3D Renderer");

        textRenderer = new TextRenderer(new Font("Dialog", Font.BOLD, 24));



        this.width = width;
        this.height = height;
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

                Matrix rotMat4D = Matrix.rotYAMatrix((float) Math.toRadians(1f));
                rotMat4D = rotMat4D.multiply(Matrix.rotXZMatrix((float) Math.toRadians(1f)));

                if (scene != null) {
                    List<WorldObject> objects = scene.getObjects();
                    for (WorldObject object : objects) {
                        //object.applyScaleRotation(rotMat4D);
                    }
                }
                drawable.display();
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

        float scaleDim;
        Matrix screenScale;
        if (width > height) {
            scaleDim = (float) height / width;
            screenScale = Matrix.scaleMatrix(scaleDim,1, 1, 1);
        } else {
            scaleDim = (float) width / height;
            screenScale = Matrix.scaleMatrix(1, scaleDim, 1, 1);
        }

        if (scene != null) {
            WorldObject object = scene.peek();
            if (object != null) {
                Matrix modelMat = object.getModelMat();

                modelMat = screenScale.multiply(modelMat);

                for (Edge4D edge : object.getEdges()) {
                    Vertex4D v1 = edge.getVertex1();
                    Vertex4D v2 = edge.getVertex2();

                    v1 = modelMat.multiplyVert(v1);
                    v2 = modelMat.multiplyVert(v2);

                    float a1 = v1.getA() + 1;
                    float z1 = v1.getZ();
                    float x1 = v1.getX() / (-z1 * a1);
                    float y1 = v1.getY() / (-z1 * a1);

                    float a2 = v2.getA() + 1;
                    float z2 = v2.getZ();
                    float x2 = v2.getX() / (-z2 * a2);
                    float y2 = v2.getY() / (-z2 * a2);

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
        //System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePos = null;
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
        int[] mouseDelta = getMouseDelta(e);
        System.out.println(mouseDelta[0] + " " + mouseDelta[1]);
        Matrix rotMat4D = Matrix.rotXZMatrix((float) Math.toRadians(-1*mouseDelta[0]));
       // Matrix rotMat4D = Matrix.rotYZMatrix((float) Math.toRadians(-1*mouseDelta[1]));
        rotMat4D = rotMat4D.multiply(Matrix.rotYZMatrix((float) Math.toRadians(-1*mouseDelta[1])));
        if (scene != null) {
            List<WorldObject> objects = scene.getObjects();
            for (WorldObject object : objects) {
                object.applyScaleRotation(rotMat4D);
            }
        }
    }

    private int[] getMouseDelta(MouseEvent e) {

        if(lastMousePos == null) {
            lastMousePos = new int[]{e.getX(), e.getY()};
            return new int[]{0,0};
        }
        else {
            int[] result = new int[]{e.getX() - lastMousePos[0], e.getY() - lastMousePos[1]};

            lastMousePos[0] = e.getX();
            lastMousePos[1] = e.getY();
            return(result);
        }
    }



    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        //System.out.println(e);
    }
}
