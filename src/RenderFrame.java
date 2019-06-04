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

    private TextRenderer textRenderer;

    private Scene scene;

    private boolean spacePressed = false;

    private char modifierSymbol = '+';

    private byte modifier = 1;

    private byte qRot = 0;
    private byte wRot = 0;
    private byte eRot = 0;





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
                //apply rotations to this matrix by pressing keys
                Matrix rotMat4D = Matrix.scaleMatrix(1,1,1,1);
                rotMat4D = rotMat4D.multiply(Matrix.rotXYMatrix((float) Math.toRadians(modifier * qRot)));
                rotMat4D = rotMat4D.multiply(Matrix.rotYAMatrix((float) Math.toRadians(modifier * wRot)));
                rotMat4D = rotMat4D.multiply(Matrix.rotZAMatrix((float) Math.toRadians(modifier * eRot)));


                if (scene != null) {
                    List<WorldObject> objects = scene.getObjects();
                    for (WorldObject object : objects) {
                        object.applyScaleRotation(rotMat4D);
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



        textRenderer.beginRendering(1000, 1000);

        if(spacePressed)
            modifierSymbol = '-';
        else
            modifierSymbol = '+';

        //Matrix rotMat4D = Matrix.rotYMatrix((float) Math.toRadians(-1*mouseDelta[0]));
        textRenderer.draw( "Reset : \'R\'", 860, 20);
        textRenderer.draw( "Model : \'T\'", 20, 110);
        textRenderer.draw( "+/- : \'Space\'", 20, 80);

        textRenderer.draw( "+/- XZ & YZ : Click and Drag", 20, 50);
        textRenderer.draw( "\'Q\' : " + modifierSymbol +  "XY    " +
                "\'W\' : " + modifierSymbol + "YA    " +
                "\'E\' : " + modifierSymbol + "ZA " , 20, 20);
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
        //T is 84
        if (e.getKeyCode() == 84)
            scene.enqueue(scene.dequeue());


        //space is 32
        if(e.getKeyCode() == 32) {
            modifier = -1;
            spacePressed = true;
        }

        //apply rotations to this matrix by pressing keys
        Matrix rotMat4D = Matrix.scaleMatrix(1,1,1,1);

        //Q is 81
        if(e.getKeyCode() == 81)
            qRot=1;

        //W is 87
        if(e.getKeyCode() == 87)
            wRot=1;

        //E is 69
        if(e.getKeyCode() == 69)
            eRot = 1;

        if (scene != null) {

            //R is 82
            if (e.getKeyCode() == 82) {
                List<WorldObject> objects = scene.getObjects();
                for (WorldObject object : objects) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if(i==j)
                                object.scaleRotMat.setVal(i,j,1.0f);
                            else
                                object.scaleRotMat.setVal(i,j,0.0f);
                        }
                    }
                }
            }

            List<WorldObject> objects = scene.getObjects();
            for (WorldObject object : objects) {
                object.applyScaleRotation(rotMat4D);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //space is 32
        if(e.getKeyCode() == 32) {
            modifier = 1;
            spacePressed = false;
        }

        //Q is 81
        if(e.getKeyCode() == 81)
            qRot=0;

        //W is 87
        if(e.getKeyCode() == 87)
            wRot=0;

        //E is 69
        if(e.getKeyCode() == 69)
            eRot = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastMousePos = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int[] mouseDelta = getMouseDelta(e);

        Matrix rotMat4D = Matrix.rotXZMatrix((float) Math.toRadians(-1*mouseDelta[0]));
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

    }
}
