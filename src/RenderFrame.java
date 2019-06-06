import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.awt.TextRenderer;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RenderFrame extends JFrame implements GLEventListener, KeyListener, MouseMotionListener, MouseListener,MenuListener, ActionListener {
  private int width;
  private int height;

    private int[] lastMousePos;

    private float mX = 0, mY = 0;

  //private JButton jb = new JButton("test");

  private TextRenderer textRenderer;
  private Scene scene;
  // private Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.WHITE);
  private JMenuBar menuBar = new JMenuBar(); // Window menu bar


    private final float ROTATION_MULT = 1.5f;

    private boolean spacePressed = false;
    private boolean lockPressed = false;
    private char modifierSymbol = '+';
    private byte modifier = 1;


    private byte[] keyRot;
    private byte[] lockRot;

    private float keyTrans;

    public RenderFrame(int width, int height) {

        super("3D Renderer");

        textRenderer = new TextRenderer(new Font("Dialog", Font.BOLD, 24));


    setJMenuBar(menuBar); // Add the menu bar to the window

    JMenu fileMenu = new JMenu("Visuals"); // Create Visuals menu
    menuBar.add(fileMenu); // Add the Visual menu
    fileMenu.setMnemonic(KeyEvent.VK_F);

    JMenu MenuItem = new JMenu("Color");//Create color Menu
    // MenuItem.setMnemonic(KeyEvent.VK_E);
    fileMenu.add(MenuItem);//Add color menu

    JMenu Menu3D = new JMenu("3D");//Create 3d Menu
    // MenuItem.setMnemonic(KeyEvent.VK_E);
    fileMenu.add(Menu3D);//Add 3d menu

    JMenu Menu4D = new JMenu("4D");//Create 4D Menu
    // MenuItem.setMnemonic(KeyEvent.VK_E);
    fileMenu.add(Menu4D);//Add color menu

    JMenu RedMenu = new JMenu("Red");
    RedMenu.addActionListener(this);
    JMenu BlueMenu = new JMenu("Blue");
    BlueMenu.addActionListener(this);
    JMenu GreenMenu = new JMenu("Green");
    GreenMenu.addActionListener(this);

    MenuItem.add(RedMenu);
    MenuItem.add(BlueMenu);
    MenuItem.add(GreenMenu);


        this.width = width;
        this.height = height;
        keyRot = new byte[6];
        lockRot = new byte[6];

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
                rotMat4D = rotMat4D.multiply(Matrix.rotXAMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[0] | lockRot[0]))));
                rotMat4D = rotMat4D.multiply(Matrix.rotYAMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[1] | lockRot[1]))));
                rotMat4D = rotMat4D.multiply(Matrix.rotZAMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[2] | lockRot[2]))));
                rotMat4D = rotMat4D.multiply(Matrix.rotXYMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[3] | lockRot[3]))));
                rotMat4D = rotMat4D.multiply(Matrix.rotYZMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[4] | lockRot[4]))));
                rotMat4D = rotMat4D.multiply(Matrix.rotXZMatrix((float) Math.toRadians(ROTATION_MULT * modifier * (keyRot[5] | lockRot[5]))));

                Matrix rotMat4D = Matrix.rotYAMatrix((float) Math.toRadians(1f));
                rotMat4D = rotMat4D.multiply(Matrix.rotXZMatrix((float) Math.toRadians(1f)));

                if (scene != null) {
                    List<WorldObject> objects = scene.getObjects();
                    for (WorldObject object : objects) {
                        object.applyTranslation(transMat4D);
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

        if (spacePressed)
            modifierSymbol = '-';
        else
            modifierSymbol = '+';

        //Matrix rotMat4D = Matrix.rotYMatrix((float) Math.toRadians(-1*mouseDelta[0]));
        textRenderer.draw( "Lock : \'Enter\'", 825, 50);
        textRenderer.draw( "Reset : \'R\'", 860, 20);

        textRenderer.draw( "Model : \'T\'", 20, 140);
        textRenderer.draw( "+/- : \'Space\'", 20, 110);

        textRenderer.draw( "+/- XZ & YZ : Click and Drag", 20, 80);
        textRenderer.draw( "\'Q\' : " + modifierSymbol +  "XA   " +
                "\'W\' : " + modifierSymbol + "YA   " +
                "\'E\' : " + modifierSymbol + "ZA " , 20, 50);
        textRenderer.draw( "\'A\' : " + modifierSymbol +  "XY    " +
                "\'S\' : " + modifierSymbol + "YZ    " +
                "\'D\' : " + modifierSymbol + "XZ " , 20, 20);
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

            boolean anyCoords4D = false;
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

                    if (a1 != 1 || a2 != 1) {
                        anyCoords4D = true;
                    }

                    if (z1 > 0 || z2 > 0) {
                        continue;
                    }

                    canvas.strokeLine(x1, y1, x2, y2);
                }
            }

            this.setTitle(anyCoords4D ? "4D Renderer" : "3D Renderer");
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

        //Enter is 10
        if (e.getKeyCode() == 10) {
            for (int i = 0; i < 6; i++) {
                lockRot[i] = keyRot[i];
            }
        }

        //space is 32
        if(e.getKeyCode() == 32) {
            modifier = -1;
            spacePressed = true;
        }

        if (e.getKeyCode() == 'T')
            scene.enqueue(scene.dequeue());

        if(e.getKeyCode() == 'Q')
            keyRot[0] = 1;

        if(e.getKeyCode() == 'W')
            keyRot[1] = 1;

        if(e.getKeyCode() == 'E')
            keyRot[2] = 1;

        if(e.getKeyCode() == 'A')
            keyRot[3] = 1;

        if(e.getKeyCode() == 'S')
            keyRot[4] = 1;

        if(e.getKeyCode() == 'D')
            keyRot[5] = 1;

        if(e.getKeyCode() == '-')
            keyTrans = 0.05f;

        if(e.getKeyCode() == '=')
            keyTrans = -0.05f;

        if (scene != null) {
            if (e.getKeyCode() == 'R') {
                List<WorldObject> objects = scene.getObjects();
                for (WorldObject object : objects) {
                    object.scaleRotMat = Matrix.scaleMatrix(1,1,1,1);
                    object.translationMat = Matrix.translationMatrix(0, 0, -5, 0);
                }

                for (int i = 0; i < 6; i++) {
                    lockRot[i] = 0;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        //space is 32
        if(e.getKeyCode() == 32) {
            modifier = 1;
            spacePressed = false;

            for (int i = 0; i < 6; i++) {
                lockRot[i] *= -1;
            }
        }

        if(e.getKeyCode() == 'Q')
            keyRot[0] = 0;

        if(e.getKeyCode() == 'W')
            keyRot[1] = 0;

        if(e.getKeyCode() == 'E')
            keyRot[2] = 0;

        if(e.getKeyCode() == 'A')
            keyRot[3] = 0;

        if(e.getKeyCode() == 'S')
            keyRot[4] = 0;

        if(e.getKeyCode() == 'D')
            keyRot[5] = 0;

        if(e.getKeyCode() == '-' || e.getKeyCode() == '=')
            keyTrans = 0;
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

        Matrix rotMat4D = Matrix.rotXZMatrix((float) Math.toRadians(-1 * mouseDelta[0]));
        rotMat4D = rotMat4D.multiply(Matrix.rotYZMatrix((float) Math.toRadians(-1 * mouseDelta[1])));
        if (scene != null) {
            List<WorldObject> objects = scene.getObjects();
            for (WorldObject object : objects) {
                object.applyScaleRotation(rotMat4D);
            }
        }
    }

    private int[] getMouseDelta(MouseEvent e) {

        if (lastMousePos == null) {
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


  @Override
  public void mouseMoved(MouseEvent e) {
    mX = e.getX();
    mY = e.getY();
    //System.out.println(e);
  }

  @Override
  public void menuSelected(MenuEvent e) {

  }

  @Override
  public void menuDeselected(MenuEvent e) {

  }

  @Override
  public void menuCanceled(MenuEvent e) {

  }


  @Override
public void actionPerformed(ActionEvent e) {
  //  if (e.getSource().equals(RedMenu)){
    //    canvas.setColor(newColor.getRed(),newColor.getGreen(),newColor.getBlue(),newColor.getAlpha());



    }
}
