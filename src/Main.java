import com.jogamp.newt.Window;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.*;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame implements GLEventListener {
    private static final long serialVersionUID = 1L;

    private int width = 600;
    private int height = 600;

    public static void main(String[] args) {
        Main main = new Main();
    }

    public Main() {
        super("3D Renderer");
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        Dimension d = new Dimension(width, height);

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setUndecorated(true);
        GLCanvas canvas = new GLCanvas(capabilities);


        canvas.setMinimumSize(d);

        canvas.addGLEventListener(this);


        this.pack();
        this.setName("3D Renderer");


        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.getContentPane().add(canvas);




        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        //this.setUndecorated(true);

        canvas.requestFocusInWindow();


        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {

        GL2 gl = glAutoDrawable.getGL().getGL2();

        //               Red      Green    Blue   Alpha
        gl.glClearColor(0.0f, 0.0f, 1f, 1f);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        //reshape(glAutoDrawable, 0,0, width, height);
        GL2 gl = glAutoDrawable.getGL().getGL2();

        //gl.glViewport(0, 0, 700, 700);
        gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);

        gl.glColor3f(1.0f, 0.0f, 0.0f );





//        gl.glBegin(GL2.GL_POLYGON);
//        gl.glVertex3f( -0.5f, -0.5f, 0.0f );
//        gl.glVertex3f( -0.5f, 0.5f, 0.0f );
//        gl.glVertex3f( 0.5f, 0.5f, 0.0f );
//        gl.glVertex3f( 0.5f, -0.5f, 0.0f );
//        gl.glEnd();

        gl.glLineWidth(10f);
        gl.glBegin(GL2.GL_LINES);

//        //left and right lines
//
//        //bot left
//        gl.glVertex3f( -0.5f, -0.5f, 0.0f );
//        //top left
//        gl.glVertex3f( -0.5f, 0.5f, 0.0f );
//
//        //bot right
//        gl.glVertex3f( 0.5f, -0.5f, 0.0f );
//        //top right
//        gl.glVertex3f( 0.5f, 0.5f, 0.0f );
//
//
//        //top and bottom lines
//
//        //bot left
//        gl.glVertex3f( -0.5f, -0.5f, 0.0f );
//        //bot right
//        gl.glVertex3f( 0.5f, -0.5f, 0.0f );
//
//
//        //top left
//        gl.glVertex3f( -0.5f, 0.5f, 0.0f );
//        //top right
//        gl.glVertex3f( 0.5f, 0.5f, 0.0f );


        //left and right lines

        //bot left
        gl.glVertex3f( -1f, -1f, 0.0f );
        //top left
        gl.glVertex3f( -1f, 1f, 0.0f );

        //bot right
        gl.glVertex3f( 1.1f, -1f, 0.0f );
        //top right
        gl.glVertex3f( 1f, 1f, 0.0f );


        //top and bottom lines

        //bot left
        gl.glVertex3f( -1f, -1f, 0.0f );
        //bot right
        gl.glVertex3f( 0.5f, -0.5f, 0.0f );


        //top left
        gl.glVertex3f( -0.5f, 0.5f, 0.0f );
        //top right
        gl.glVertex3f( 0.5f, 0.5f, 0.0f );

        gl.glEnd();

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        System.out.println(i + " "+ i1 + " " + i2 + " " + i3 + " ");
        gl.glViewport(i, i1, i2, i3);
    }
}
